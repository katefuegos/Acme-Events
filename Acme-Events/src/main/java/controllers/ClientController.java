
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.ClientService;
import services.ConfigurationService;
import services.ParticipationEventService;
import domain.Client;
import domain.ParticipationEvent;
import forms.ActorForm;

@Controller
@RequestMapping("/client")
public class ClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClientService				clientService;

	@Autowired
	private ConfigurationService		configurationService;

	@Autowired
	private ParticipationEventService	participationEventService;


	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int clientId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Client client = null;

		try {

			client = this.clientService.findOne(clientId);
			Assert.notNull(client);

			final ActorForm actorForm = new ActorForm();
			actorForm.setId(client.getId());
			actorForm.setName(client.getName());
			actorForm.setMiddleName(client.getMiddleName());
			actorForm.setEmail(client.getEmail());
			actorForm.setPhoto(client.getPhoto());
			actorForm.setPhone(client.getPhone());
			actorForm.setSurname(client.getSurname());
			actorForm.setAddress(client.getAddress());
			actorForm.setIsBanned(client.getIsBanned());
			actorForm.setIsSuspicious(client.getIsSuspicious());

			result = this.ShowModelAndView(actorForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/client/list.do");
			if (client == null)
				redirectAttrs.addFlashAttribute("message", "client.commit.error");
		}

		return result;
	}

	// SHOW
	@RequestMapping(value = "/showByParticipationEvent", method = RequestMethod.GET)
	public ModelAndView showByParticipationEvent(final int participationEventId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Client client = null;
		ParticipationEvent participationEvent = null;
		try {

			participationEvent = this.participationEventService.findOne(participationEventId);
			Assert.notNull(participationEvent);
			client = participationEvent.getClient();
			Assert.notNull(client);

			final ActorForm actorForm = new ActorForm();
			actorForm.setId(client.getId());
			actorForm.setName(client.getName());
			actorForm.setMiddleName(client.getMiddleName());
			actorForm.setEmail(client.getEmail());
			actorForm.setPhoto(client.getPhoto());
			actorForm.setPhone(client.getPhone());
			actorForm.setSurname(client.getSurname());
			actorForm.setAddress(client.getAddress());
			actorForm.setIsBanned(client.getIsBanned());
			actorForm.setIsSuspicious(client.getIsSuspicious());

			result = this.ShowModelAndView(actorForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/client/list.do");
			if (client == null)
				redirectAttrs.addFlashAttribute("message", "client.commit.error");
		}

		return result;
	}

	protected ModelAndView ShowModelAndView(final ActorForm actorForm) {
		ModelAndView result;
		result = this.ShowModelAndView(actorForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final ActorForm actorForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("client/show");
		result.addObject("message", message);
		result.addObject("requestURI", "client/show.do?clientId=" + actorForm.getId());
		result.addObject("actorForm", actorForm);
		result.addObject("isRead", true);
		result.addObject("id", actorForm.getId());
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
