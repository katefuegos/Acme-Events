
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

import services.ClubService;
import services.ConfigurationService;
import services.EventService;
import services.ManagerService;
import services.OpinionService;
import domain.Club;
import domain.Event;
import domain.Opinion;

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

}
