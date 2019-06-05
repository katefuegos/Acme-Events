/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.ActorService;
import services.ClientService;
import services.ConfigurationService;
import domain.Actor;
import domain.Client;
import forms.ActorForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private ConfigurationService	configurationService;


	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final ActorForm actorForm = new ActorForm();

		final Authority manager = new Authority();
		manager.setAuthority(Authority.MANAGER);
		final Authority client = new Authority();
		client.setAuthority(Authority.CLIENT);
		final Authority publiciter = new Authority();
		publiciter.setAuthority(Authority.PUBLICITER);
		final Authority admin = new Authority();
		admin.setAuthority(Authority.ADMIN);

		actorForm.setDNI("---");
		try {
			final Actor a = this.actorService.findByUserAccount(LoginService.getPrincipal());
			Assert.notNull(a);

			if (a.getUserAccount().getAuthorities().contains(manager))
				actorForm.setAuth("MANAGER");

			else if (a.getUserAccount().getAuthorities().contains(client)) {
				actorForm.setAuth("CLIENT");
				final Client cli = this.clientService.findClientByUseraccount(a.getUserAccount());

				actorForm.setDNI(cli.getDNI());

			} else if (a.getUserAccount().getAuthorities().contains(admin))
				actorForm.setAuth("ADMIN");
			else if (a.getUserAccount().getAuthorities().contains(publiciter))
				actorForm.setAuth("PUBLICITER");
			else
				throw new NullPointerException();

			actorForm.setUserAccount(a.getUserAccount());
			actorForm.setId(a.getId());
			actorForm.setVersion(a.getVersion());
			actorForm.setName(a.getName());
			actorForm.setSurname(a.getSurname());
			actorForm.setMiddleName(a.getMiddleName());
			actorForm.setPhoto(a.getPhoto());
			actorForm.setEmail(a.getEmail());
			actorForm.setPhone(a.getPhone());
			actorForm.setAddress(a.getAddress());

			result = this.createEditModelAndView(actorForm);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
	//
	// Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(actorForm);
		else
			try {

				this.actorService.update(actorForm);
				result = this.createEditModelAndView(actorForm, "actor.commit.ok");

			} catch (final Throwable oops) {
				if (oops.getClass() == NumberFormatException.class)
					result = this.createEditModelAndView(actorForm, "actor.creditcard.error.invalid");
				else if (oops.getMessage() == "actor.creditcard.error.date.invalid")
					result = this.createEditModelAndView(actorForm, oops.getMessage());
				else
					result = this.createEditModelAndView(actorForm, "actor.commit.error");

			}
		return result;

	}

	// CreateModelAndView

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;
		final Authority client = new Authority();
		client.setAuthority(Authority.CLIENT);

		if (actorForm.getUserAccount().getAuthorities().contains(client)) {
			actorForm.setAuth("CLIENT");
			final Client cli = this.clientService.findClientByUseraccount(actorForm.getUserAccount());
			actorForm.setDNI(cli.getDNI());
		}

		result = new ModelAndView("actor/edit");
		result.addObject("actorForm", actorForm);

		result.addObject("message", message);
		result.addObject("isRead", false);
		result.addObject("requestURI", "actor/edit.do");
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

	//	@RequestMapping(value = "/show", method = RequestMethod.GET)
	//	public ModelAndView show(@RequestParam final int actorId, final RedirectAttributes redirectAttrs) {
	//		ModelAndView modelAndView = new ModelAndView("actor/edit");
	//
	//		final Actor actor = this.actorService.findOne(actorId);
	//		final Company company = this.companyService.findOne(actorId);
	//		final domain.Rookie rookie = this.rookieService.findOne(actorId);
	//
	//		try {
	//			Assert.notNull(actor);
	//			final ActorForm actorForm = new ActorForm();
	//
	//			if (company != null)
	//				actorForm.setComercialName(company.getComercialName());
	//			else if (rookie != null)
	//				actorForm.setComercialName("---");
	//
	//			actorForm.setUserAccount(actor.getUserAccount());
	//			actorForm.setName(actor.getName());
	//			actorForm.setSurname(actor.getSurnames());
	//			actorForm.setPhoto(actor.getPhoto());
	//			actorForm.setEmail(actor.getEmail());
	//			actorForm.setPhone(actor.getPhone());
	//			actorForm.setAddress(actor.getAddress());
	//
	//			modelAndView.addObject("actor", actor);
	//			modelAndView.addObject("isRead", true);
	//			modelAndView.addObject("requestURI", "/actor/administrator/show.do?actorId=" + actorId);
	//			modelAndView.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
	//			modelAndView.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
	//
	//		} catch (final Throwable e) {
	//			modelAndView = new ModelAndView("redirect:/welcome/index.do");
	//			if (actor == null)
	//				redirectAttrs.addFlashAttribute("message", "actor.error.unexist");
	//		}
	//		return modelAndView;
	//
	//	}

}
