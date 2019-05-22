
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ClubService;
import services.ConfigurationService;
import domain.Club;

@Controller
@RequestMapping("/club")
public class ClubController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService				clubService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Club> clubs = this.clubService.findClubsAccepted();
		result = new ModelAndView("club/list");

		result.addObject("clubs", clubs);
		result.addObject("requestURI", "club/list.do");
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}
}
