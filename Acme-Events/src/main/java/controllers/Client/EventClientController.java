
package controllers.Client;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ClientService;
import services.ClubService;
import services.ConfigurationService;
import services.EventService;
import controllers.AbstractController;
import domain.Client;
import domain.Club;
import domain.Event;

@Controller
@RequestMapping("/event/client")
public class EventClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private ClubService				clubService;

	@Autowired
	private EventService			eventService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------------------------

	public EventClientController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "-1") final int clubId) {
		ModelAndView result;
		try {

			final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
			final Club club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			Assert.isTrue(club.getId() == this.clubService.findClubByClient(client.getId(), club.getId()).getId());

			Collection<Event> events;
			if (clubId == -1)
				events = this.eventService.findEventsByFollower(client);
			else
				events = this.eventService.findEventsByFollowerAndClub(client, club);

			result = new ModelAndView("event/client/myList");

			result.addObject("events", events);
			result.addObject("client", client);
			result.addObject("requestURI", "event/client/myList.do");

			result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
			result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
	//	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	//	public ModelAndView search(@Valid final SearchForm searchForm, final BindingResult binding) {
	//		ModelAndView result;
	//		if (binding.hasErrors())
	//			result = this.editModelAndView(searchForm, "commit.error");
	//		else
	//			try {
	//				final Category category = this.categoryService.findOne(categoryForm.getId());
	//				final Map<String, String> map = new HashMap<String, String>();
	//				map.put("EN", categoryForm.getNameEN());
	//				map.put("ES", categoryForm.getNameES());
	//				category.setTitle(map);
	//				category.setRootCategory(categoryForm.getRootcategory());
	//
	//				this.categoryService.save(category);
	//				result = new ModelAndView("redirect:/category/administrator/list.do");
	//			} catch (final Throwable oops) {
	//
	//				result = this.editModelAndView(searchForm, "commit.error");
	//			}
	//
	//		return result;
	//	}

}
