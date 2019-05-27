/*
 * WelcomeController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Services

	@Autowired
	ConfigurationService	configurationService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "Acme Events") final String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		final Map<String, String> welcomeMessages = this.configurationService.findOne().getWelcomeMessage();
		final String welcomeMessage = this.configurationService.internacionalizcion(welcomeMessages);
		final String banner = this.configurationService.findOne().getBanner();

		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("banner", banner);
		result.addObject("moment", moment);
		result.addObject("welomeMessage", welcomeMessage);

		return result;
	}

	@RequestMapping(value = "/terms")
	public ModelAndView terms() {

		ModelAndView result;
		final String lang = LocaleContextHolder.getLocale().getLanguage().toString().toUpperCase();
		result = new ModelAndView("misc/terms");
		result.addObject("lang", lang);
		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}
}
