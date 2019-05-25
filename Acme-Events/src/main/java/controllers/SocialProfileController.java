/*
 * ProfileController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.ActorService;
import services.ConfigurationService;
import services.SocialProfileService;
import domain.Actor;
import domain.SocialProfile;
import forms.SocialProfileForm;

@Controller
@RequestMapping("/socialProfile")
public class SocialProfileController extends AbstractController {

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private SocialProfileService	socialProfileService;

	@Autowired
	private ActorService			actorService;


	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final int uaId = LoginService.getPrincipal().getId();
		final Collection<SocialProfile> socialProfiles = this.socialProfileService.findProfileByUserAccount(uaId);
		result = new ModelAndView("socialProfile/list");

		result.addObject("socialProfiles", socialProfiles);
		result.addObject("requestURI", "socialProfile/list.do");
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		final SocialProfileForm socialProfileForm = new SocialProfileForm();
		Actor actor = null;
		try {
			actor = this.actorService.findActorByUsername(LoginService.getPrincipal().getUsername());
			Assert.notNull(actor);

			socialProfileForm.setActor(actor);
			socialProfileForm.setId(0);

			result = this.createModelAndView(socialProfileForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/socialProfile/list.do");
			if (actor == null)
				redirectAttrs.addFlashAttribute("message", "commit.error");

		}
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialProfileForm socialProfileForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(socialProfileForm, "commit.error");
		else
			try {
				final SocialProfile socialProfile = this.socialProfileService.create();
				socialProfile.setName(socialProfileForm.getName());
				socialProfile.setLink(socialProfileForm.getLink());
				socialProfile.setNick(socialProfileForm.getNick());
				socialProfile.setActor(socialProfileForm.getActor());

				this.socialProfileService.save(socialProfile);

				result = new ModelAndView("redirect:/socialProfile/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(socialProfileForm, "commit.error");
			}
		return result;
	}

	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int socialProfileId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		final SocialProfileForm socialProfileForm = new SocialProfileForm();
		SocialProfile socialProfile = null;
		Actor actor = null;

		try {
			actor = this.actorService.findActorByUsername(LoginService.getPrincipal().getUsername());
			Assert.notNull(actor);
			socialProfile = this.socialProfileService.findOne(socialProfileId);
			Assert.notNull(socialProfile);
			Assert.isTrue(socialProfile.getActor().equals(actor));
			socialProfileForm.setId(socialProfile.getId());
			socialProfileForm.setActor(socialProfile.getActor());
			socialProfileForm.setNick(socialProfile.getNick());
			socialProfileForm.setName(socialProfile.getName());
			socialProfileForm.setLink(socialProfile.getLink());

			result = this.editModelAndView(socialProfileForm);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/socialProfile/list.do");
			if (actor == null)
				redirectAttrs.addFlashAttribute("message", "commit.error");
			else if (socialProfile == null)
				redirectAttrs.addFlashAttribute("message", "socialProfile.error.unexist");
			else if (!this.socialProfileService.findOne(socialProfileId).getActor().equals(actor))
				redirectAttrs.addFlashAttribute("message", "socialProfile.error.notFromRookie");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final SocialProfileForm socialProfileForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.editModelAndView(socialProfileForm, "commit.error");
		else
			try {
				final SocialProfile socialProfile = this.socialProfileService.findOne(socialProfileForm.getId());
				final Actor b = this.actorService.findActorByUsername(LoginService.getPrincipal().getUsername());
				Assert.isTrue(socialProfile.getActor().getId() == b.getId());

				socialProfile.setName(socialProfileForm.getName());
				socialProfile.setLink(socialProfileForm.getLink());
				socialProfile.setActor(socialProfileForm.getActor());
				socialProfile.setNick(socialProfileForm.getNick());

				this.socialProfileService.save(socialProfile);

				result = new ModelAndView("redirect:/socialProfile/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(socialProfileForm, "commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final SocialProfileForm socialProfileForm, final BindingResult binding) {
		ModelAndView result;
		final Actor b = null;
		try {
			Assert.notNull(socialProfileForm);
			final SocialProfile socialProfile = this.socialProfileService.findOne(socialProfileForm.getId());
			Assert.isTrue(socialProfile.getActor().equals(b));

			this.socialProfileService.delete(this.socialProfileService.findOne(socialProfileForm.getId()));

			result = new ModelAndView("redirect:/socialProfile/socialProfileor/list.do");
		} catch (final Throwable oops) {

			result = this.editModelAndView(socialProfileForm, "socialProfile.commit.error");
		}
		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int socialProfileId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		SocialProfile socialProfile = null;
		final Actor b = null;

		try {
			socialProfile = this.socialProfileService.findOne(socialProfileId);
			Assert.notNull(socialProfile);
			Assert.isTrue(socialProfile.getActor().getId() == b.getId());
			final SocialProfileForm socialProfileForm = new SocialProfileForm();
			socialProfileForm.setId(socialProfile.getId());
			socialProfileForm.setActor(socialProfile.getActor());
			socialProfileForm.setNick(socialProfile.getNick());
			socialProfileForm.setLink(socialProfile.getLink());
			socialProfileForm.setName(socialProfile.getName());

			result = this.ShowModelAndView(socialProfileForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/socialProfile/list.do");
			if (this.socialProfileService.findOne(socialProfileId) == null)
				redirectAttrs.addFlashAttribute("message", "socialProfile.error.unexist	");
			else if (!this.socialProfileService.findOne(socialProfileId).getActor().equals(b))
				redirectAttrs.addFlashAttribute("message", "socialProfile.error.notFromRookie");
		}
		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final SocialProfileForm socialProfileForm) {
		ModelAndView result;
		result = this.createModelAndView(socialProfileForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final SocialProfileForm socialProfileForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("socialProfile/create");

		result.addObject("message", message);
		result.addObject("requestURI", "socialProfile/create.do");
		result.addObject("socialProfileForm", socialProfileForm);
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final SocialProfileForm socialProfileForm) {
		ModelAndView result;
		result = this.editModelAndView(socialProfileForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final SocialProfileForm socialProfileForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("socialProfile/edit");

		result.addObject("message", message);
		result.addObject("requestURI", "socialProfile/edit.do?socialProfileId=" + socialProfileForm.getId());
		result.addObject("socialProfileForm", socialProfileForm);
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final SocialProfileForm socialProfileForm) {
		ModelAndView result;
		result = this.ShowModelAndView(socialProfileForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final SocialProfileForm socialProfileForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("socialProfile/show");
		result.addObject("message", message);
		result.addObject("requestURI", "socialProfile/socialProfileor/show.do?socialProfileId=" + socialProfileForm.getId());
		result.addObject("socialProfileForm", socialProfileForm);
		result.addObject("id", socialProfileForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

}
