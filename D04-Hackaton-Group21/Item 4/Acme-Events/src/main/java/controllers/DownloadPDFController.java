package controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.AdministratorService;
import services.ClientService;
import services.ConfigurationService;
import services.ManagerService;
import services.PubliciterService;
import services.SocialProfileService;
import domain.Administrator;
import domain.Client;
import domain.Manager;
import domain.SocialProfile;

@Controller
@RequestMapping("/data")
public class DownloadPDFController {

	@Autowired
	ClientService clientService;

	@Autowired
	ManagerService managerService;

	@Autowired
	PubliciterService publiciterService;

	@Autowired
	AdministratorService administratorService;

	@Autowired
	ConfigurationService configurationService;

	@Autowired
	ActorService actorService;

	@Autowired
	SocialProfileService socialProfileService;

	@Autowired
	UserAccountService userAccountService;

	// List
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView;

		modelAndView = new ModelAndView("misc/data");
		modelAndView.addObject("requestURI", "/data/list.do");
		modelAndView.addObject("banner", this.configurationService.findAll()
				.iterator().next().getBanner());
		modelAndView.addObject("systemName", this.configurationService
				.findAll().iterator().next().getSystemName());

		return modelAndView;
	}

	@RequestMapping(value = "/downloadPersonalData")
	public void downloadPersonalData(HttpServletResponse response)
			throws IOException {

		String csvFileName = "personalData.csv";
		response.setContentType("text/csv");

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		response.setHeader(headerKey, headerValue);

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
				CsvPreference.STANDARD_PREFERENCE);

		UserAccount userAccount = LoginService.getPrincipal();
		if (userAccount.getAuthorities().toString().contains("CLIENT")) {

			Client c1 = clientService.findClientByUseraccount(userAccount);

			List<Client> listClient = Arrays.asList(c1);

			String[] header = { "name", "middleName", "surname", "photo",
					"email", "phone", "address", "DNI" };

			csvWriter.writeHeader(header);

			for (Client client : listClient) {
				csvWriter.write(client, header);
			}

		} else if (userAccount.getAuthorities().toString().contains("MANAGER")) {

			Manager m1 = managerService.findManagerByUserAccount(userAccount
					.getId());

			List<Manager> listManager = Arrays.asList(m1);

			String[] header = { "name", "middleName", "surname", "photo",
					"email", "phone", "address" };

			csvWriter.writeHeader(header);

			for (Manager manager : listManager) {
				csvWriter.write(manager, header);
			}

		} else if (userAccount.getAuthorities().toString().contains("ADMIN")) {

			Administrator a1 = administratorService
					.findByUseraccount(userAccount);

			List<Administrator> listAdministrator = Arrays.asList(a1);

			String[] header = { "name", "middleName", "surname", "photo",
					"email", "phone", "address" };

			csvWriter.writeHeader(header);

			for (Administrator administrator : listAdministrator) {
				csvWriter.write(administrator, header);
			}
		}

		csvWriter.close();
	}

	@RequestMapping(value = "/deletePersonalData", method = RequestMethod.GET)
	public ModelAndView deletePersonalData(
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		UserAccount userAccount = LoginService.getPrincipal();

		try {
			Assert.notNull(userAccount);
			if (userAccount.getAuthorities().toString().contains("CLIENT")) {
				Client c = clientService.findClientByUseraccount(userAccount);
				Assert.notNull(c);
				c.setAddress(null);
				c.setEmail("null@null.null");
				c.setName("null");
				c.setCreditCard(null);
				c.setDNI("null");
				c.setPhone(null);
				c.setPhoto(null);
				c.setMiddleName(null);
				c.setSurname("null");
				userAccount.setEnabled(false);
				Collection<SocialProfile> socialProfiles = socialProfileService
						.findProfileByUserAccount(userAccount.getId());
				if (!socialProfiles.isEmpty()) {
					for (SocialProfile s : socialProfiles) {
						socialProfileService.delete(s);
					}
				}
				clientService.save(c);
				userAccountService.save(userAccount);

			} else if (userAccount.getAuthorities().toString()
					.contains("MANAGER")) {
				Manager m = managerService.findManagerByUserAccount(userAccount
						.getId());
				Assert.notNull(m);
				m.setAddress(null);
				m.setEmail("null@null.null");
				m.setName("null");
				m.setPhone(null);
				m.setPhoto(null);
				m.setMiddleName(null);
				m.setSurname("null");
				userAccount.setEnabled(false);
				Collection<SocialProfile> socialProfiles = socialProfileService
						.findProfileByUserAccount(userAccount.getId());
				if (!socialProfiles.isEmpty()) {
					for (SocialProfile s : socialProfiles) {
						socialProfileService.delete(s);
					}
				}
				managerService.save(m);
				userAccountService.save(userAccount);

			} else {
				Assert.isTrue(false);
			}

			result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/data/list.do");

			if (LoginService.getPrincipal().getAuthorities().toString()
					.contains("ADMIN"))
				redirectAttrs.addFlashAttribute("message",
						"misc.error.adminNotDelete");
			else
				redirectAttrs.addFlashAttribute("message", "commit.error");
		}
		return result;
	}
}