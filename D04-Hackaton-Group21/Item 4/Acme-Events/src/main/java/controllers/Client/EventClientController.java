
package controllers.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.ClientService;
import services.ClubService;
import services.ConfigurationService;
import services.EventService;
import services.ParticipationEventService;
import controllers.AbstractController;
import domain.Client;
import domain.Club;
import domain.Event;
import domain.ParticipationEvent;
import forms.SearchForm;

@Controller
@RequestMapping("/event/client")
public class EventClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService					clubService;

	@Autowired
	private EventService				eventService;

	@Autowired
	private ClientService				clientService;

	@Autowired
	private ParticipationEventService	participationEventService;

	@Autowired
	private ConfigurationService		configurationService;


	// Constructor---------------------------------------------------------

	public EventClientController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "-1") final String clubId) {
		ModelAndView result;
		try {

			final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());

			ArrayList<Collection<Event>> events;
			if (clubId.equals("-1"))
				events = this.eventService.listEventsByFollower(client);
			else {
				final Club club = this.clubService.findOne(Integer.valueOf(clubId));
				Assert.notNull(club);
				Assert.isTrue(club.getId() == this.clubService.findClubByClient(client.getId(), club.getId()).getId());

				events = this.eventService.listEventsByFollowerAndClub(client, club);
			}

			final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();

			result = new ModelAndView("event/client/myList");

			result.addObject("eventsAvailable", events.get(0));
			result.addObject("eventsFinished", events.get(1));
			result.addObject("eventsCancelled", events.get(2));
			result.addObject("varTax", this.configurationService.findAll().iterator().next().getVarTax());
			result.addObject("searchForm", new SearchForm());
			result.addObject("client", client);
			result.addObject("lang", lang);
			result.addObject("requestURI", "event/client/myList.do");

			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@Valid final SearchForm searchForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.listModelAndView(searchForm, null, "message.commit.error");
		else
			try {

				final Collection<Event> events = this.eventService.searchEvent(searchForm);

				result = this.listModelAndView(searchForm, events, "actor.commit.ok");
			} catch (final Throwable oops) {

				result = this.listModelAndView(searchForm, null, "message.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/participate", method = RequestMethod.GET)
	public ModelAndView participate(final int eventId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Event event = null;
		Client client = null;
		try {
			event = this.eventService.findOne(eventId);
			Assert.notNull(event);
			Assert.isTrue(!event.isDraftMode());
			client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
			Assert.notNull(client);
			Assert.notNull(client.getCreditCard());
			Assert.isTrue(this.eventService.findEventsByFollower(client).contains(event));
			Assert.isTrue(this.clubService.findClubByClient(client.getId(), event.getClub().getId()) != null);
			Assert.isTrue(event.getMomentEnd().after(new Date()));
			Assert.isTrue(event.getStatus().equals("AVAILABLE"));
			final Collection<ParticipationEvent> pes1 = event.getParticipationsEvent();
			for (final ParticipationEvent e : pes1)
				Assert.isTrue(!e.getClient().equals(client));

			ParticipationEvent participationEvent = this.participationEventService.create();
			participationEvent = this.participationEventService.save(participationEvent);
			event.getParticipationsEvent().add(participationEvent);
			this.eventService.participate(event);

			result = new ModelAndView("redirect:/participationEvent/client/list.do");

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/event/client/myList.do");
			if (event == null)
				redirectAttrs.addFlashAttribute("message", "event.error.unexist");
			else if(event.isDraftMode())
				redirectAttrs.addFlashAttribute("message", "event.error.participateDraft");
			else if (client.getCreditCard() == null)
				redirectAttrs.addFlashAttribute("message", "event.error.creditCardBad");
			else if (!this.eventService.findEventsByFollower(client).contains(event))
				redirectAttrs.addFlashAttribute("message", "event.error.alreadyParticipate");
			else if (this.clubService.findClubByClient(client.getId(), event.getClub().getId()) == null)
				redirectAttrs.addFlashAttribute("message", "club.error.follow.unexist2");
			else if(event.getMomentEnd().before(new Date()))
				redirectAttrs.addFlashAttribute("message", "event.error.participateFinish");
			else if(!event.getStatus().equals("AVAILABLE"))
				redirectAttrs.addFlashAttribute("message", "event.error.cancelled");
			else
				redirectAttrs.addFlashAttribute("message", "message.commit.error");
		}

		return result;
	}

	protected ModelAndView listModelAndView(final SearchForm searchForm, Collection<Event> events, final String message) {
		ModelAndView result;
		try {

			final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
			Assert.notNull(client);
			final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();

			if (events == null)
				events = this.eventService.findEventsByFollower(client);

			result = new ModelAndView("event/listFinal");

			result.addObject("events", events);
			result.addObject("searchForm", searchForm);
			result.addObject("varTax", this.configurationService.findAll().iterator().next().getVarTax());
			result.addObject("client", client);
			result.addObject("lang", lang);
			result.addObject("message", message);
			result.addObject("requestURI", "event/list.do");

			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/event/list.do");
		}

		return result;
	}

}
