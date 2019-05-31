
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CategoryService;
import services.ClubService;
import services.ConfigurationService;
import services.EventService;
import services.ManagerService;
import services.OpinionService;
import domain.Club;
import domain.Event;
import domain.Opinion;
import forms.EventManagerForm;
import forms.SearchForm;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService				clubService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private EventService			eventService;

	@Autowired
	private OpinionService			opinionService;

	@Autowired
	private CategoryService			categoryService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------

	// Lists ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "0") final String clubId) {
		ModelAndView result;

		try {
			final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();

			Collection<Event> events = null;
			if (clubId.equals("0"))
				events = this.eventService.findFinalModel();
			else {

				final Club club = this.clubService.findOne(Integer.valueOf(clubId));
				Assert.notNull(club);

				events = this.eventService.findFinalModel(Integer.valueOf(clubId));
			}

			result = new ModelAndView("event/listFinal");
			result.addObject("events", events);
			result.addObject("searchForm", new SearchForm());
			result.addObject("lang", lang);
			result.addObject("requestURI", "event/list.do");

			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/opinions", method = RequestMethod.GET)
	public ModelAndView listOpinions(@RequestParam(required = false, defaultValue = "0") final String eventId) {
		ModelAndView result;

		try {

			final int id = Integer.valueOf(eventId);

			final Event e = this.eventService.findOne(id);
			Assert.notNull(e);

			final Collection<Opinion> opinions = this.opinionService.findByEvent(e);

			result = new ModelAndView("opinion/event/list");

			result.addObject("opinions", opinions);
			result.addObject("event", e);
			result.addObject("requestURI", "event/opinions.do?eventId=" + eventId);
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int eventId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Event event = null;

		try {
			event = this.eventService.findOne(eventId);
			Assert.notNull(event);
			Assert.isTrue(!event.isDraftMode());

			final EventManagerForm eventManagerForm = new EventManagerForm();
			eventManagerForm.setAddress(event.getAddress());
			eventManagerForm.setDescription(event.getDescription());
			eventManagerForm.setDraftMode(event.isDraftMode());
			eventManagerForm.setClub(event.getClub());
			eventManagerForm.setCategory(event.getCategory());
			eventManagerForm.setMomentEnd(event.getMomentEnd());
			eventManagerForm.setMomentStart(event.getMomentStart());
			eventManagerForm.setPoster(event.getPoster());
			eventManagerForm.setPrice(event.getPrice());
			eventManagerForm.setTitle(event.getTitle());

			result = this.ShowModelAndView(eventManagerForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/welcome/index.do");

		}

		return result;
	}

	protected ModelAndView ShowModelAndView(final EventManagerForm eventManagerForm) {
		ModelAndView result;
		result = this.ShowModelAndView(eventManagerForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final EventManagerForm eventManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("event/manager/show");
		result.addObject("message", message);
		result.addObject("requestURI", "event/manager/show.do?eventId=" + eventManagerForm.getId());
		result.addObject("eventManagerForm", eventManagerForm);
		result.addObject("id", eventManagerForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

}
