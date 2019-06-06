
package controllers.Manager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ConfigurationService;
import services.EventService;
import services.ManagerService;
import services.OpinionService;
import controllers.AbstractController;
import domain.Event;
import domain.Manager;
import domain.Opinion;
import forms.OpinionForm3;

@Controller
@RequestMapping("/opinion/manager")
public class OpinionManagerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private OpinionService			opinionService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private EventService			eventService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------------------------

	public OpinionManagerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Manager manager = this.managerService.findManagerByUserAccount(LoginService.getPrincipal().getId());

		final Collection<Opinion> opinions = this.opinionService.findByManager(manager);

		final Collection<OpinionForm3> opinionsForms = new ArrayList<OpinionForm3>();
		for (final Opinion o : opinions) {
			final Event event = this.eventService.findByOpinionForm(o);
			final OpinionForm3 form = new OpinionForm3();
			form.setDescription(o.getDescription());
			form.setScore(o.getScore());
			form.setEvent(event);
			form.setTitle(o.getTitle());
			form.setTitleEvent(event.getTitle());
			form.setMoment(o.getMoment());
			opinionsForms.add(form);
		}

		final Collection<Event> events = this.eventService.findByManager(manager);

		result = new ModelAndView("opinion/list");

		result.addObject("opinionsForms", opinionsForms);
		result.addObject("events", events);
		result.addObject("requestURI", "opinion/manager/list.do");

		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

}
