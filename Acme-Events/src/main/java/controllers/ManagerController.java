package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import security.UserAccount;
import services.ClubService;
import services.ConfigurationService;
import services.ManagerService;
import services.SocialProfileService;
import domain.Club;
import domain.Manager;
import domain.SocialProfile;
import forms.ActorForm;

@Controller
@RequestMapping("/manager")
public class ManagerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private SocialProfileService socialProfileService;

	// Constructor---------------------------------------

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Manager> managers = this.managerService.findAll();
		result = new ModelAndView("manager/list");

		result.addObject("managers", managers);
		result.addObject("requestURI", "manager/list.do");
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());

		return result;
	}

	@RequestMapping(value = "/listClubs", method = RequestMethod.GET)
	public ModelAndView listClubs() {
		ModelAndView result;
		final UserAccount ua = LoginService.getPrincipal();
		Manager manager = managerService.findManagerByUserAccount(ua.getId());
		int managerId = manager.getId();
		final Collection<Club> clubs = this.clubService
				.findByManagerAndAccepted(managerId);
		final Collection<Club> clubsPending = this.clubService
				.findByManagerIdAndPending(managerId);
		final Collection<Club> clubsRejected = this.clubService
				.findByManagerIdAndRejected(managerId);
		final Collection<Club> clubsDraftMode = this.clubService
				.findByManagerIdAndDraftMode(managerId);
		result = new ModelAndView("manager/listClubs");

		result.addObject("clubs", clubs);
		result.addObject("clubsPending", clubsPending);
		result.addObject("clubsRejected", clubsRejected);
		result.addObject("clubsDraftMode", clubsDraftMode);
		result.addObject("requestURI", "manager/listClubs.do");
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());
		return result;
	}

	@RequestMapping(value = "/listProfiles", method = RequestMethod.GET)
	public ModelAndView listProfiles(final int managerId) {
		ModelAndView result;

		final Collection<SocialProfile> socialProfiles = this.socialProfileService
				.findProfilesByActor(managerId);
		result = new ModelAndView("manager/listProfiles");

		result.addObject("socialProfiles", socialProfiles);
		result.addObject("requestURI", "manager/listProfiles.do");
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());

		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int managerId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Manager manager = null;

		try {

			manager = this.managerService.findOne(managerId);
			Assert.notNull(manager);

			final ActorForm actorForm = new ActorForm();
			actorForm.setId(manager.getId());
			actorForm.setName(manager.getName());
			actorForm.setMiddleName(manager.getMiddleName());
			actorForm.setEmail(manager.getEmail());
			actorForm.setPhoto(manager.getPhoto());
			actorForm.setPhone(manager.getPhone());
			actorForm.setSurname(manager.getSurname());
			actorForm.setAddress(manager.getAddress());
			actorForm.setIsBanned(manager.getIsBanned());
			actorForm.setIsSuspicious(manager.getIsSuspicious());

			result = this.ShowModelAndView(actorForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message",
						"manager.commit.error");
		}

		return result;
	}

	// SHOW
	@RequestMapping(value = "/showByClub", method = RequestMethod.GET)
	public ModelAndView showByClub(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Manager manager = null;
		Club club = null;
		try {

			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			manager = club.getManager();
			Assert.notNull(manager);

			final ActorForm actorForm = new ActorForm();
			actorForm.setId(manager.getId());
			actorForm.setName(manager.getName());
			actorForm.setMiddleName(manager.getMiddleName());
			actorForm.setEmail(manager.getEmail());
			actorForm.setPhoto(manager.getPhoto());
			actorForm.setPhone(manager.getPhone());
			actorForm.setSurname(manager.getSurname());
			actorForm.setAddress(manager.getAddress());
			actorForm.setIsBanned(manager.getIsBanned());
			actorForm.setIsSuspicious(manager.getIsSuspicious());

			result = this.ShowModelAndView(actorForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message",
						"manager.commit.error");
		}

		return result;
	}

	protected ModelAndView ShowModelAndView(final ActorForm actorForm) {
		ModelAndView result;
		result = this.ShowModelAndView(actorForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final ActorForm actorForm,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("manager/show");
		result.addObject("message", message);
		result.addObject("requestURI",
				"manager/show.do?managerId=" + actorForm.getId());
		result.addObject("actorForm", actorForm);
		result.addObject("isRead", true);
		result.addObject("id", actorForm.getId());
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());
		return result;
	}
}
