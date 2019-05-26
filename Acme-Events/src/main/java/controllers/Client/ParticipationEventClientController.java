package controllers.Client;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ClientService;
import services.ConfigurationService;
import services.EventService;
import services.ParticipationEventService;
import controllers.AbstractController;
import domain.Client;
import domain.Event;
import domain.ParticipationEvent;
import forms.ParticipationEventForm;

@Controller
@RequestMapping("/participationEvent/client")
public class ParticipationEventClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ParticipationEventService participationEventService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EventService eventService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructor---------------------------------------------------------

	public ParticipationEventClientController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Client client = this.clientService
				.findClientByUseraccount(LoginService.getPrincipal());

		final Collection<ParticipationEvent> participationEvents = this.participationEventService
				.findByClientId(client.getId());

		Collection<ParticipationEventForm> participationEventForms = new ArrayList<ParticipationEventForm>();
		for (ParticipationEvent e : participationEvents) {
			Event event = eventService.findByParticipationForm(e);
			ParticipationEventForm form = new ParticipationEventForm();
			form.setCreditCardNumber(e.getCreditCardNumber());
			form.setMoment(e.getMoment());
			form.setTicker(event.getTicker());
			form.setTitle(event.getTitle());
			participationEventForms.add(form);
		}

		result = new ModelAndView("participationEvent/list");

		result.addObject("participationEventForms", participationEventForms);
		result.addObject("requestURI", "participationEvent/client/list.do");
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());

		return result;
	}
}
