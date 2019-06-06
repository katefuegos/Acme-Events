package controllers.Client;

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
import services.ClientService;
import services.ClubService;
import services.ConfigurationService;
import controllers.AbstractController;
import domain.Client;
import domain.Club;

@Controller
@RequestMapping("/club/client")
public class ClubClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService clubService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructor---------------------------------------------------------

	public ClubClientController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Client client = this.clientService
				.findClientByUseraccount(LoginService.getPrincipal());

		final Collection<Club> clubs = this.clubService.findByClient(client);
		final Collection<Club> otherClubs = this.clubService
				.findClubsAccepted();
		otherClubs.removeAll(clubs);

		result = new ModelAndView("club/client/myList");

		result.addObject("myClubs", clubs);
		result.addObject("otherClubs", otherClubs);
		result.addObject("client", client);
		result.addObject("requestURI", "club/client/myList.do");
		result.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll()
				.iterator().next().getSystemName());

		return result;
	}

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;
		Client client = null;
		UserAccount ua = null;
		try {
			ua = LoginService.getPrincipal();
			Assert.notNull(ua);
			client = clientService.findClientByUseraccount(ua);
			Assert.notNull(client);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(clubService.findClubByClient(client.getId(),
					club.getId()) == null);

			this.clubService.followClub(club);

			result = new ModelAndView("redirect:/club/client/myList.do");
		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/client/myList.do");
			if (client == null)
				redirectAttrs.addFlashAttribute("message",
						"message.commit.error");
			else if (club == null)
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (clubService.findClubByClient(client.getId(), club.getId()) != null)
				redirectAttrs.addFlashAttribute("message",
						"club.error.follow.exist");
			else
				redirectAttrs.addFlashAttribute("message",
						"message.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollow(final int clubId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;
		Client client = null;
		UserAccount ua = null;

		try {
			ua = LoginService.getPrincipal();
			Assert.notNull(ua);
			client = clientService.findClientByUseraccount(ua);
			Assert.notNull(client);
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(clubService.findClubByClient(client.getId(),
					club.getId()) != null);

			this.clubService.unFollowClub(club);

			result = new ModelAndView("redirect:/club/client/myList.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/club/client/myList.do");
			if (client == null)
				redirectAttrs.addFlashAttribute("message",
						"message.commit.error");
			else if (club == null)
				redirectAttrs
						.addFlashAttribute("message", "club.error.unexist");
			else if (clubService.findClubByClient(client.getId(), club.getId()) == null)
				redirectAttrs.addFlashAttribute("message",
						"club.error.follow.unexist");
			else
				redirectAttrs.addFlashAttribute("message",
						"message.commit.error");
		}
		return result;
	}

}
