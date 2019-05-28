
package controllers.Manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import security.UserAccount;
import services.CategoryService;
import services.ClubService;
import services.ConfigurationService;
import services.EventService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Category;
import domain.Club;
import domain.Event;
import domain.Manager;
import forms.EventManagerForm;

@Controller
@RequestMapping("/event/manager")
public class EventManagerController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private EventService			eventService;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private ClubService				clubService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private CategoryService			categoryService;


	// Constructor---------------------------------------

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int clubId) {
		ModelAndView result;

		final Collection<Event> events = this.eventService.findEventsByClub(clubId);
		final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
		result = new ModelAndView("event/manager/list");

		result.addObject("events", events);
		result.addObject("lang", lang);
		result.addObject("requestURI", "event/manager/list.do");
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final Integer clubId, final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		Club club = null;
		Collection<Category> categories;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			club = this.clubService.findOne(clubId);
			Assert.notNull(club);
			categories = this.categoryService.findAll();

			final EventManagerForm eventManagerForm = new EventManagerForm();
			eventManagerForm.setId(0);
			eventManagerForm.setClub(club);

			result = this.createModelAndView(eventManagerForm);
			result.addObject("categories", categories);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/event/manager/list.do");
			if (club == null)
				redirectAttrs.addFlashAttribute("message", "event.commit.error");

		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EventManagerForm eventManagerForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(eventManagerForm, "event.commit.error");
		else
			try {
				final Event event = this.eventService.create();
				event.setAddress(eventManagerForm.getAddress());
				event.setDescription(eventManagerForm.getDescription());
				event.setDraftMode(eventManagerForm.isDraftMode());
				event.setClub(eventManagerForm.getClub());
				event.setCategory(eventManagerForm.getCategory());
				event.setMomentEnd(eventManagerForm.getMomentEnd());
				event.setMomentPublished(eventManagerForm.getMomentPublished());
				event.setMomentStart(eventManagerForm.getMomentStart());
				event.setPoster(eventManagerForm.getPoster());
				event.setPrice(eventManagerForm.getPrice());
				event.setStatus(eventManagerForm.getStatus());
				event.setTicker(eventManagerForm.getTicker());
				event.setTitle(eventManagerForm.getTitle());

				this.eventService.save(event);

				result = new ModelAndView("redirect:/event/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(eventManagerForm, "event.commit.error");
			}

		return result;
	}

	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int eventId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Event event = null;
		Manager manager = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount.getId());
			Assert.notNull(manager);
			event = this.eventService.findOne(eventId);
			Assert.notNull(event);
			Assert.isTrue(event.isDraftMode());

			final EventManagerForm eventManagerForm = new EventManagerForm();
			eventManagerForm.setAddress(event.getAddress());
			eventManagerForm.setDescription(event.getDescription());
			eventManagerForm.setDraftMode(event.isDraftMode());
			eventManagerForm.setClub(event.getClub());
			eventManagerForm.setCategory(event.getCategory());
			eventManagerForm.setMomentEnd(event.getMomentEnd());
			eventManagerForm.setMomentPublished(event.getMomentPublished());
			eventManagerForm.setMomentStart(event.getMomentStart());
			eventManagerForm.setPoster(event.getPoster());
			eventManagerForm.setPrice(event.getPrice());
			eventManagerForm.setStatus(event.getStatus());
			eventManagerForm.setTicker(event.getTicker());
			eventManagerForm.setTitle(event.getTitle());

			result = this.editModelAndView(eventManagerForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/event/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "event.commit.error");
			else if (event == null)
				redirectAttrs.addFlashAttribute("message", "event.error.unexist");
			else if (!event.isDraftMode())
				redirectAttrs.addFlashAttribute("message", "event.error.notDraft");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final EventManagerForm eventManagerForm, final BindingResult binding) {
		ModelAndView result;
		Manager manager = null;
		Event event = null;

		if (binding.hasErrors())
			result = this.editModelAndView(eventManagerForm, "event.commit.error");
		else
			try {
				final UserAccount userAccount = LoginService.getPrincipal();
				manager = this.managerService.findManagerByUserAccount(userAccount.getId());
				Assert.notNull(manager);
				event = this.eventService.findOne(eventManagerForm.getId());
				Assert.notNull(event);
				event.setAddress(eventManagerForm.getAddress());
				event.setDescription(eventManagerForm.getDescription());
				event.setDraftMode(eventManagerForm.isDraftMode());
				event.setClub(eventManagerForm.getClub());
				event.setCategory(eventManagerForm.getCategory());
				event.setMomentEnd(eventManagerForm.getMomentEnd());
				event.setMomentPublished(eventManagerForm.getMomentPublished());
				event.setMomentStart(eventManagerForm.getMomentStart());
				event.setPoster(eventManagerForm.getPoster());
				event.setPrice(eventManagerForm.getPrice());
				event.setStatus(eventManagerForm.getStatus());
				event.setTicker(eventManagerForm.getTicker());
				event.setTitle(eventManagerForm.getTitle());

				this.eventService.save(event);

				result = new ModelAndView("redirect:/event/manager/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(eventManagerForm, "event.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final EventManagerForm eventManagerForm, final BindingResult binding) {
		ModelAndView result;
		Manager manager = null;
		Event event = null;

		if (binding.hasErrors())
			result = this.editModelAndView(eventManagerForm, "event.commit.error");
		else
			try {
				final UserAccount userAccount = LoginService.getPrincipal();
				manager = this.managerService.findManagerByUserAccount(userAccount.getId());
				Assert.notNull(manager);
				event = this.eventService.findOne(eventManagerForm.getId());
				Assert.notNull(event);
				Assert.isTrue(event.getClub().equals(manager));

				this.eventService.delete(event);

				result = new ModelAndView("redirect:/event/manager/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(eventManagerForm, "event.commit.error");
			}

		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int eventId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Manager manager = null;
		Event event = null;

		try {
			final UserAccount userAccount = LoginService.getPrincipal();
			manager = this.managerService.findManagerByUserAccount(userAccount.getId());
			Assert.notNull(manager);
			event = this.eventService.findOne(eventId);
			Assert.notNull(event);
			Assert.isTrue(event.getClub().equals(manager));

			final EventManagerForm eventManagerForm = new EventManagerForm();
			eventManagerForm.setAddress(event.getAddress());
			eventManagerForm.setDescription(event.getDescription());
			eventManagerForm.setDraftMode(event.isDraftMode());
			eventManagerForm.setClub(event.getClub());
			eventManagerForm.setCategory(event.getCategory());
			eventManagerForm.setMomentEnd(event.getMomentEnd());
			eventManagerForm.setMomentPublished(event.getMomentPublished());
			eventManagerForm.setMomentStart(event.getMomentStart());
			eventManagerForm.setPoster(event.getPoster());
			eventManagerForm.setPrice(event.getPrice());
			eventManagerForm.setStatus(event.getStatus());
			eventManagerForm.setTicker(event.getTicker());
			eventManagerForm.setTitle(event.getTitle());

			result = this.ShowModelAndView(eventManagerForm);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/event/manager/list.do");
			if (manager == null)
				redirectAttrs.addFlashAttribute("message", "event.commit.error");
			else if (event == null)
				redirectAttrs.addFlashAttribute("message", "event.error.unexist");
			else if (!event.getClub().equals(manager))
				redirectAttrs.addFlashAttribute("message", "event.error.notFromManager");
		}

		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final EventManagerForm eventManagerForm) {
		ModelAndView result;
		result = this.createModelAndView(eventManagerForm, null);
		return result;
	}

	protected ModelAndView createModelAndView(final EventManagerForm eventManagerForm, final String message) {
		final ModelAndView result;

		final Collection<Category> categories = this.categoryService.findAll();
		result = new ModelAndView("event/create");

		result.addObject("categories", categories);
		result.addObject("message", message);
		result.addObject("requestURI", "event/manager/create.do");
		result.addObject("eventManagerForm", eventManagerForm);
		result.addObject("isRead", false);
		result.addObject("id", eventManagerForm.getId());
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView editModelAndView(final EventManagerForm eventManagerForm) {
		ModelAndView result;
		result = this.editModelAndView(eventManagerForm, null);
		return result;
	}

	protected ModelAndView editModelAndView(final EventManagerForm eventManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("event/edit");
		result.addObject("message", message);
		result.addObject("requestURI", "event/manager/edit.do?eventId=" + eventManagerForm.getId());
		result.addObject("eventManagerForm", eventManagerForm);
		result.addObject("id", eventManagerForm.getId());
		result.addObject("isRead", false);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}

	protected ModelAndView ShowModelAndView(final EventManagerForm eventManagerForm) {
		ModelAndView result;
		result = this.ShowModelAndView(eventManagerForm, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(final EventManagerForm eventManagerForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("event/show");
		result.addObject("message", message);
		result.addObject("requestURI", "event/manager/show.do?eventId=" + eventManagerForm.getId());
		result.addObject("eventManagerForm", eventManagerForm);
		result.addObject("id", eventManagerForm.getId());
		result.addObject("isRead", true);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return result;
	}
}
