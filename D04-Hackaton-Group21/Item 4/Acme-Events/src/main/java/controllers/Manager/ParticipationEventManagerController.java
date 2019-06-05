
package controllers.Manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.ConfigurationService;
import services.EventService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Event;
import domain.Manager;
import domain.ParticipationEvent;

@Controller
@RequestMapping("/participationEvent/manager")
public class ParticipationEventManagerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ManagerService				managerService;

	@Autowired
	private EventService				eventService;

	@Autowired
	private ConfigurationService		configurationService;


	// Constructor---------------------------------------------------------

	public ParticipationEventManagerController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int eventId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Event event = null;
		Manager manager = null;
		try {
			event = this.eventService.findOne(eventId);
			Assert.notNull(event);
			manager = this.managerService.findManagerByUserAccount(LoginService.getPrincipal().getId());
			Assert.notNull(manager);
			Assert.isTrue(this.eventService.findByManager(manager).contains(event));
			Assert.isTrue(!event.isDraftMode());
			Assert.isTrue(event.getClub().getManager().equals(manager));
			final Collection<ParticipationEvent> participationEvents = event.getParticipationsEvent();

			result = new ModelAndView("participationEvent/manager/list");

			result.addObject("participationEvents", participationEvents);
			result.addObject("requestURI", "participationEvent/manager/list.do");
			result.addObject("event", event);

			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/");
			if (event == null)
				redirectAttrs.addFlashAttribute("message", "event.error.unexist");
			else if (event.isDraftMode())
				redirectAttrs.addFlashAttribute("message", "event.error.draft");
			else if (!event.getClub().getManager().equals(manager))
				redirectAttrs.addFlashAttribute("message", "event.error.notFromThisActor");
			else 
				redirectAttrs.addFlashAttribute("message", "message.commit.error");
		}

		return result;

	}
}
