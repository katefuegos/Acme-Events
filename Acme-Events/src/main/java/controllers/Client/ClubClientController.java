
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
import services.ClientService;
import services.ClubService;
import services.ConfigurationService;
import services.FollowService;
import controllers.AbstractController;
import domain.Client;
import domain.Club;

@Controller
@RequestMapping("/club/client")
public class ClubClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService				clubService;

	@Autowired
	private FollowService			followService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------------------------

	public ClubClientController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());

		final Collection<Club> clubs = this.clubService.findByClient(client);
		final Collection<Club> otherClubs = this.clubService.findAll();
		otherClubs.removeAll(clubs);

		result = new ModelAndView("club/client/myList");

		result.addObject("myClubs", clubs);
		result.addObject("otherClubs", otherClubs);
		result.addObject("client", client);
		result.addObject("requestURI", "club/client/myList.do");
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(final int clubId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;

		try {
			club = this.clubService.findOne(clubId);
			Assert.isTrue(club != null, "message.commit.error");

			this.clubService.followClub(club);

			result = new ModelAndView("redirect:/club/client/myList.do");
		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/client/myList.do");

			redirectAttrs.addFlashAttribute("message", "message.commit.error");
		}
		return result;
	}

	//	@RequestMapping(value = "/unFollow", method = RequestMethod.GET)
	//	public ModelAndView edit(final int clubId, final RedirectAttributes redirectAttrs) {
	//		ModelAndView result;
	//		Club club = null;
	//
	//		try {
	//			club = this.clubService.findOne(clubId);
	//			Assert.isTrue(club != null, "message.commit.error");
	//
	//			this.clubService.followClub(club);
	//
	//			result = this.list();
	//		} catch (final Throwable e) {
	//
	//			result = new ModelAndView("redirect:/club/list.do");
	//
	//			redirectAttrs.addFlashAttribute("message", "message.commit.error");
	//		}
	//		return result;
	//	}

}
