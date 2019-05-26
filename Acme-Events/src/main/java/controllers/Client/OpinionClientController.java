
package controllers.Client;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ClientService;
import services.ConfigurationService;
import services.EventService;
import services.OpinionService;
import controllers.AbstractController;
import domain.Client;
import domain.Event;
import domain.Opinion;
import forms.OpinionForm;
import forms.OpinionForm2;

@Controller
@RequestMapping("/opinion/client")
public class OpinionClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private OpinionService			opinionService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private EventService			eventService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------------------------

	public OpinionClientController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());

		final Collection<Opinion> opinions = this.opinionService.findByClient(client);

		Collection<OpinionForm2> opinionsForms = new ArrayList<OpinionForm2>();
		for (Opinion o : opinions) {
			Event event = eventService.findByOpinionForm(o);
			OpinionForm2 form = new OpinionForm2();
			form.setDescription(o.getDescription());
			form.setScore(o.getScore());
			form.setTicker(event.getTicker());
			form.setTitle(o.getTitle());
			form.setTitleEvent(event.getTitle());
			form.setMoment(o.getMoment());
			opinionsForms.add(form);
		}
		
		result = new ModelAndView("opinion/list");

		result.addObject("opinionsForms", opinionsForms);
		result.addObject("requestURI", "opinion/client/list.do");

		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final OpinionForm opinionForm = new OpinionForm();

		result = this.createModelAndView(opinionForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final OpinionForm opinionForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createModelAndView(opinionForm, "message.commit.error");
		else
			try {

				final int score = Integer.valueOf(opinionForm.getScore());

				final Opinion opinion = this.opinionService.create();
				opinion.setTitle(opinionForm.getTitle());
				opinion.setDescription(opinionForm.getDescription());
				opinion.setScore(score);

				final Event event = opinionForm.getEvent();
				final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
				final Collection<Event> events = this.eventService.findOpinionable(client);

				Assert.isTrue(events.contains(event), "opinion.error.notEvent");

				final Opinion saved = this.opinionService.save(opinion);
				event.getOpinions().add(saved);
				this.eventService.save(event);

				result = new ModelAndView("redirect:/opinion/client/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(opinionForm, "message.commit.error");
			}
		return result;
	}

	// AUXILIARY METHODS

	protected ModelAndView createModelAndView(final OpinionForm opinionForm) {
		ModelAndView result;
		result = this.createModelAndView(opinionForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final OpinionForm opinionForm, final String message) {
		ModelAndView result;

		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
		final Collection<Event> events = this.eventService.findOpinionable(client);

		if (events.isEmpty()) {
			result = this.list();
			result.addObject("message", "opinion.error.notEvent");
		} else {
			result = new ModelAndView("opinion/create");
			result.addObject("opinionForm", opinionForm);
			result.addObject("events", events);
			result.addObject("message", message);
			result.addObject("requestURI", "opinion/client/create.do");
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		}

		return result;
	}
}
