
package controllers.Client;

import java.util.Collection;

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

import security.LoginService;
import services.ClientService;
import services.ClubService;
import services.ConfigurationService;
import services.EventService;
import controllers.AbstractController;
import domain.Client;
import domain.Club;
import domain.Event;
import forms.SearchForm;

@Controller
@RequestMapping("/event/client")
public class EventClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService				clubService;

	@Autowired
	private EventService			eventService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private ConfigurationService	configurationService;


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

			Collection<Event> events;
			if (clubId.equals("-1"))
				events = this.eventService.findEventsByFollower(client);
			else {
				final Club club = this.clubService.findOne(Integer.valueOf(clubId));
				Assert.notNull(club);
				Assert.isTrue(club.getId() == this.clubService.findClubByClient(client.getId(), club.getId()).getId());

				events = this.eventService.findEventsByFollowerAndClub(client, club);
			}

			final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();

			result = new ModelAndView("event/client/myList");

			result.addObject("events", events);
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

				final Collection<Event> events = this.eventService.searchPosition(searchForm);

				result = this.listModelAndView(searchForm, events, "actor.commit.ok");
			} catch (final Throwable oops) {

				result = this.listModelAndView(searchForm, null, "message.commit.error");
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

			result = new ModelAndView("event/client/myList");

			result.addObject("events", events);
			result.addObject("searchForm", searchForm);
			result.addObject("client", client);
			result.addObject("lang", lang);
			result.addObject("message", message);
			result.addObject("requestURI", "event/client/myList.do");

			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/event/client/myList.do");
		}

		return result;
	}

}
