package controllers.Administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import controllers.AbstractController;

import security.LoginService;
import security.UserAccount;
import services.ClubService;
import services.ConfigurationService;
import domain.Club;

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
	public ModelAndView cancel(final int clubId,
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
			Assert.isTrue(club.getReasonReject().equals(null));
			this.clubService.reject(club);

			result = new ModelAndView("redirect:/club/administrator/list.do");

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/administrator/list.do");
			if (club.equals(null))
				redirectAttrs.addFlashAttribute("message",
						"club.error.unexist");
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
}
