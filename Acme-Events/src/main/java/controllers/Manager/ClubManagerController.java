package controllers.Manager;

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
import security.UserAccount;
import services.ClubService;
import services.ConfigurationService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Club;
import domain.Follow;
import domain.Manager;
import forms.ClubManagerForm;

@Controller
@RequestMapping("/club/manager")
public class ClubManagerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService clubService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructor---------------------------------------

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		Manager manager = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount
					.getId());
			Assert.notNull(manager);

			final ClubManagerForm clubManagerForm = new ClubManagerForm();
			clubManagerForm.setId(0);
			clubManagerForm.setManager(manager);

			result = this.createModelAndView(clubManagerForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/manager/listClubs.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");

		}

		return result;
	}

	@RequestMapping(value = "/listFollows", method = RequestMethod.GET)
	public ModelAndView listFollows(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;
		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount
					.getId());
			Assert.notNull(manager);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(club.getManager().equals(manager));
			Assert.isTrue(!club.isDraftMode());
			final Collection<Follow> follows = club.getFollows();
			result = new ModelAndView("club/manager/listFollows");

			result.addObject("follows", follows);
			result.addObject("requestURI", "club/manager/listFollows.do");
			result.addObject("banner", this.configurationService.findAll()
					.iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll()
					.iterator().next().getSystemName());
		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/manager/listClubs.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");
			else if (club == null)
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (!club.getManager().equals(manager))
				redirectAttrs.addFlashAttribute("message",
						"club.error.notFromThisActor");
			else if (club.isDraftMode())
				redirectAttrs.addFlashAttribute("message",
						"club.error.isDraft");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ClubManagerForm clubManagerForm,
			final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(clubManagerForm,
					"club.commit.error");
		else
			try {
				final Club club = this.clubService.create();
				club.setAddress(clubManagerForm.getAddress());
				club.setDescription(clubManagerForm.getDescription());
				club.setDraftMode(clubManagerForm.isDraftMode());
				club.setManager(clubManagerForm.getManager());
				club.setName(clubManagerForm.getName());
				club.setPictures(clubManagerForm.getPictures());

				this.clubService.save(club);

				result = new ModelAndView("redirect:/manager/listClubs.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(clubManagerForm,
						"club.commit.error");
			}

		return result;
	}

	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;
		Manager manager = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount
					.getId());
			Assert.notNull(manager);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(club.isDraftMode());
			Assert.isTrue(club.getManager().equals(manager));
			final ClubManagerForm clubManagerForm = new ClubManagerForm();
			clubManagerForm.setId(club.getId());
			clubManagerForm.setAddress(club.getAddress());
			clubManagerForm.setDescription(club.getDescription());
			clubManagerForm.setDraftMode(club.isDraftMode());
			clubManagerForm.setName(club.getName());
			clubManagerForm.setPictures(club.getPictures());
			clubManagerForm.setManager(club.getManager());

			result = this.editModelAndView(clubManagerForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/manager/listClubs.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");
			else if (club == null)
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (!club.getManager().equals(manager))
				redirectAttrs.addFlashAttribute("message",
						"club.error.notFromThisActor");
			else if (!club.isDraftMode())
				redirectAttrs.addFlashAttribute("message",
						"club.error.notDraft");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final ClubManagerForm clubManagerForm,
			final BindingResult binding) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;

		if (binding.hasErrors())
			result = this
					.editModelAndView(clubManagerForm, "club.commit.error");
		else
			try {
				final UserAccount userAccount = LoginService.getPrincipal();
				manager = this.managerService
						.findManagerByUserAccount(userAccount.getId());
				Assert.notNull(manager);
				club = this.clubService.findOne(clubManagerForm.getId());
				Assert.notNull(club);
				club.setAddress(clubManagerForm.getAddress());
				club.setDescription(clubManagerForm.getDescription());
				club.setDraftMode(clubManagerForm.isDraftMode());
				club.setDraftMode(clubManagerForm.isDraftMode());
				club.setManager(clubManagerForm.getManager());
				club.setName(clubManagerForm.getName());
				club.setPictures(clubManagerForm.getPictures());

				this.clubService.save(club);

				result = new ModelAndView("redirect:/manager/listClubs.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(clubManagerForm,
						"club.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final ClubManagerForm clubManagerForm,
			final BindingResult binding) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;

		if (binding.hasErrors())
			result = this
					.editModelAndView(clubManagerForm, "club.commit.error");
		else
			try {
				final UserAccount userAccount = LoginService.getPrincipal();
				manager = this.managerService
						.findManagerByUserAccount(userAccount.getId());
				Assert.notNull(manager);
				club = this.clubService.findOne(clubManagerForm.getId());
				Assert.notNull(club);
				Assert.isTrue(club.getManager().equals(manager));

				this.clubService.delete(club);

				result = new ModelAndView("redirect:/manager/listClubs.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(clubManagerForm,
						"club.commit.error");
			}

		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount
					.getId());
			Assert.notNull(manager);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(club.getManager().equals(manager));
			Assert.isTrue(!club.isDraftMode());
			final ClubManagerForm clubManagerForm = new ClubManagerForm();
			clubManagerForm.setId(club.getId());
			clubManagerForm.setAddress(club.getAddress());
			clubManagerForm.setDescription(club.getDescription());
			clubManagerForm.setDraftMode(club.isDraftMode());
			clubManagerForm.setName(club.getName());
			clubManagerForm.setPictures(club.getPictures());
			clubManagerForm.setManager(club.getManager());

			result = this.ShowModelAndView(clubManagerForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/manager/listClubs.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "club.commit.error");
			else if (club == null)
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (!club.getManager().equals(manager))
				redirectAttrs.addFlashAttribute("message",
						"club.error.notFromThisActor");
			else if (club.isDraftMode())
				redirectAttrs
						.addFlashAttribute("message", "club.error.isDraft");
		}

		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(
			final ClubManagerForm clubManagerForm) {
		ModelAndView result;
		result = this.createModelAndView(clubManagerForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(
			final ClubManagerForm clubManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/create");

		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/create.do");
		result.addObject("clubManagerForm", clubManagerForm);
		result.addObject("isRead", false);
		result.addObject("id", clubManagerForm.getId());
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(
			final ClubManagerForm clubManagerForm) {
		ModelAndView result;
		result = this.editModelAndView(clubManagerForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(
			final ClubManagerForm clubManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/edit");
		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/edit.do?clubId="
				+ clubManagerForm.getId());
		result.addObject("clubManagerForm", clubManagerForm);
		result.addObject("id", clubManagerForm.getId());
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(
			final ClubManagerForm clubManagerForm) {
		ModelAndView result;
		result = this.ShowModelAndView(clubManagerForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(
			final ClubManagerForm clubManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/show");
		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/show.do?clubId="
				+ clubManagerForm.getId());
		result.addObject("clubManagerForm", clubManagerForm);
		result.addObject("id", clubManagerForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());
		return result;
	}
}
