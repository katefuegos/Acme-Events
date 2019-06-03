
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.ClubService;
import services.ConfigurationService;
import domain.Club;
import forms.ClubManagerForm;

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
	//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//	public ModelAndView list() {
	//		ModelAndView result;
	//
	//		final Collection<Club> clubsAccepted = this.clubService.findClubsAccepted();
	//		final Collection<Club> clubsCanceled = this.clubService.findClubsRejected();
	//		result = new ModelAndView("club/list");
	//
	//		result.addObject("clubsAccepted", clubsAccepted);
	//		result.addObject("clubsCanceled", clubsCanceled);
	//		result.addObject("requestURI", "club/list.do");
	//		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
	//		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
	//
	//		return result;
	//	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "0") final String managerId, @RequestParam(required = false, defaultValue = "0") final String eventId) {
		ModelAndView result;

		try {
			Collection<Club> clubs;

			if (eventId.equals("0"))
				clubs = this.clubService.findClubsAccepted();
			else {
				final int id = Integer.valueOf(eventId);
				clubs = this.clubService.findClubsAccepted(id);
			}

			if (!managerId.equals("0")) {
				final int id = Integer.valueOf(managerId);
				clubs = this.clubService.findByManagerAndAcepted(id);
			}

			result = new ModelAndView("club/list");

			result.addObject("clubsAccepted", clubs);
			result.addObject("requestURI", "club/list.do");
			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int clubId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Club club = null;

		try {

			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(!club.isDraftMode());

			final ClubManagerForm clubManagerForm = new ClubManagerForm();
			clubManagerForm.setId(club.getId());
			clubManagerForm.setAddress(club.getAddress());
			clubManagerForm.setDescription(club.getDescription());
			clubManagerForm.setDraftMode(club.isDraftMode());
			clubManagerForm.setName(club.getName());
			clubManagerForm.setPictures(club.getPictures());
			clubManagerForm.setManager(club.getManager());
			clubManagerForm.setScore(club.getScore());

			result = this.ShowModelAndView(clubManagerForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/club/list.do");

			redirectAttrs.addFlashAttribute("message", "club.commit.error");

		}

		return result;
	}

	protected ModelAndView ShowModelAndView(final ClubManagerForm clubManagerForm) {
		ModelAndView result;
		result = this.ShowModelAndView(clubManagerForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final ClubManagerForm clubManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("club/show");
		result.addObject("message", message);
		result.addObject("requestURI", "club/manager/show.do?clubId=" + clubManagerForm.getId());
		result.addObject("clubManagerForm", clubManagerForm);
		result.addObject("id", clubManagerForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
