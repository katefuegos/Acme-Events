
package controllers.Manager;

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
import security.UserAccount;
import services.ClubService;
import services.ConfigurationService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Club;
import domain.Manager;
import forms.ClubForm;

@Controller
@RequestMapping("/club/manager")
public class ClubManagerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService				clubService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		Manager manager = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount.getId());
			Assert.notNull(manager);

			final ClubForm clubForm = new ClubForm();
			clubForm.setId(0);
			clubForm.setManager(manager);

			result = this.createModelAndView(clubForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/club/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ClubForm clubForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(clubForm, "club.commit.error");
		else
			try {
				final Club club = this.clubService.create();
				club.setAddress(clubForm.getAddress());
				club.setDescription(clubForm.getDescription());
				club.setDraftMode(clubForm.isDraftMode());
				club.setDraftMode(clubForm.isDraftMode());
				club.setManager(clubForm.getManager());
				club.setName(clubForm.getName());
				club.setPictures(clubForm.getPictures());
				club.setReasonReject(clubForm.getReasonReject());

				this.clubService.save(club);

				result = new ModelAndView("redirect:/club/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(clubForm, "club.commit.error");
			}

		return result;
	}

	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int clubId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;
		Manager manager = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount.getId());
			Assert.notNull(manager);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(club.getManager().equals(manager));

			final ClubForm clubForm = new ClubForm();
			clubForm.setId(club.getId());
			clubForm.setAddress(club.getAddress());
			clubForm.setDescription(club.getDescription());
			clubForm.setDraftMode(club.isDraftMode());
			clubForm.setName(club.getName());
			clubForm.setPictures(club.getPictures());
			clubForm.setReasonReject(club.getReasonReject());
			clubForm.setManager(club.getManager());

			result = this.editModelAndView(clubForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");
			else if (club == null)
				redirectAttrs.addFlashAttribute("message", "club.error.unexist");
			else if (!club.getManager().equals(manager))
				redirectAttrs.addFlashAttribute("message", "club.error.notFromManager");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final ClubForm clubForm, final BindingResult binding) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;

		if (binding.hasErrors())
			result = this.editModelAndView(clubForm, "club.commit.error");
		else
			try {
				final UserAccount userAccount = LoginService.getPrincipal();
				manager = this.managerService.findManagerByUserAccount(userAccount.getId());
				Assert.notNull(manager);
				club = this.clubService.findOne(clubForm.getId());
				Assert.notNull(club);
				Assert.isTrue(club.getManager().equals(manager));

				club.setAddress(clubForm.getAddress());
				club.setDescription(clubForm.getDescription());
				club.setDraftMode(clubForm.isDraftMode());
				club.setDraftMode(clubForm.isDraftMode());
				club.setManager(clubForm.getManager());
				club.setName(clubForm.getName());
				club.setPictures(clubForm.getPictures());
				club.setReasonReject(clubForm.getReasonReject());

				this.clubService.save(club);

				result = new ModelAndView("redirect:/club/manager/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(clubForm, "club.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final ClubForm clubForm, final BindingResult binding) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;

		if (binding.hasErrors())
			result = this.editModelAndView(clubForm, "club.commit.error");
		else
			try {
				final UserAccount userAccount = LoginService.getPrincipal();
				manager = this.managerService.findManagerByUserAccount(userAccount.getId());
				Assert.notNull(manager);
				club = this.clubService.findOne(clubForm.getId());
				Assert.notNull(club);
				Assert.isTrue(club.getManager().equals(manager));

				this.clubService.delete(club);

				result = new ModelAndView("redirect:/club/manager/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(clubForm, "club.commit.error");
			}

		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int clubId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount.getId());
			Assert.notNull(manager);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(club.getManager().equals(manager));

			final ClubForm clubForm = new ClubForm();
			clubForm.setId(club.getId());
			clubForm.setAddress(club.getAddress());
			clubForm.setDescription(club.getDescription());
			clubForm.setDraftMode(club.isDraftMode());
			clubForm.setName(club.getName());
			clubForm.setPictures(club.getPictures());
			clubForm.setReasonReject(club.getReasonReject());
			clubForm.setManager(club.getManager());

			result = this.ShowModelAndView(clubForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");
			else if (club == null)
				redirectAttrs.addFlashAttribute("message", "club.error.unexist");
			else if (!club.getManager().equals(manager))
				redirectAttrs.addFlashAttribute("message", "club.error.notFromManager");
		}

		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final ClubForm clubForm) {
		ModelAndView result;
		result = this.createModelAndView(clubForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final ClubForm clubForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/create");

		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/create.do");
		result.addObject("clubForm", clubForm);
		result.addObject("isRead", false);
		result.addObject("id", clubForm.getId());
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final ClubForm clubForm) {
		ModelAndView result;
		result = this.editModelAndView(clubForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final ClubForm clubForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/edit");
		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/edit.do?clubId=" + clubForm.getId());
		result.addObject("clubForm", clubForm);
		result.addObject("id", clubForm.getId());
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final ClubForm clubForm) {
		ModelAndView result;
		result = this.ShowModelAndView(clubForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final ClubForm clubForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/show");
		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/show.do?clubId=" + clubForm.getId());
		result.addObject("clubForm", clubForm);
		result.addObject("id", clubForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
