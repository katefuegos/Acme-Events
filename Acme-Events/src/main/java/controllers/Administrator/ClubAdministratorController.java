package controllers.Administrator;

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
import controllers.AbstractController;
import domain.Club;
import forms.ClubForm;

@Controller
@RequestMapping("/club/administrator")
public class ClubAdministratorController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService clubService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructor---------------------------------------

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Club> pendingClubs = this.clubService
				.findClubsPending();
		final Collection<Club> rejectedClubs = this.clubService
				.findClubsRejected();
		final Collection<Club> acceptedClubs = this.clubService
				.findClubsAccepted();
		result = new ModelAndView("club/list2");

		result.addObject("pendingClubs", pendingClubs);
		result.addObject("rejectedClubs", rejectedClubs);
		result.addObject("acceptedClubs", acceptedClubs);
		result.addObject("requestURI", "club/administrator/list.do");
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());

		return result;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;
		UserAccount ua = null;
		try {
			club = this.clubService.findOne(clubId);
			ua = LoginService.getPrincipal();
			Assert.notNull(ua);
			Assert.isTrue(ua.getAuthorities().toString().contains("ADMIN"));
			Assert.notNull(club);
			Assert.isTrue(!club.isAccepted());
			Assert.isTrue(club.getReasonReject() == null);
			this.clubService.accept(club);

			result = new ModelAndView("redirect:/club/administrator/list.do");

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/administrator/list.do");
			if (club.equals(null))
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (club.isAccepted())
				redirectAttrs.addFlashAttribute("message",
						"club.error.alreadyAccepted");
			else if (!club.getReasonReject().equals(null))
				redirectAttrs.addFlashAttribute("message",
						"club.error.alreadyRejected");
			else
				redirectAttrs.addFlashAttribute("message", "commit.error");
		}
		return result;
	}

	// EDIT

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		final ClubForm clubForm = new ClubForm();
		Club club = null;

		try {
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(!club.isAccepted());
			Assert.isTrue(club.getReasonReject() == null);

			clubForm.setId(clubId);

			result = this.rejectModelAndView(clubForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/administrator/list.do");
			if (club == null)
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (club.isAccepted() == true)
				redirectAttrs.addFlashAttribute("message",
						"club.error.alreadyAccepted");
			else if (!(club.getReasonReject() == null))
				redirectAttrs.addFlashAttribute("message",
						"club.error.alreadyRejected");
		}
		return result;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, params = "save")
	public ModelAndView reject(@Valid final ClubForm clubForm,
			final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.rejectModelAndView(clubForm, "commit.error");
		else
			try {
				this.clubService.reject(clubForm);
				result = new ModelAndView(
						"redirect:/club/administrator/list.do");
			} catch (final Throwable oops) {

				result = this.rejectModelAndView(clubForm, "commit.error");
			}

		return result;
	}

	// RejectModelAndView
	protected ModelAndView rejectModelAndView(final ClubForm clubForm) {
		ModelAndView result;
		result = this.rejectModelAndView(clubForm, null);
		return result;
	}

	protected ModelAndView rejectModelAndView(final ClubForm clubForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("club/reject");
		result.addObject("message", message);
		result.addObject("clubForm", clubForm);
		result.addObject("requestURI", "club/administrator/reject.do?clubId="
				+ clubForm.getId());
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());

		return result;
	}
}
