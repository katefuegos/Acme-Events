/*
 * AbstractController.java
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

import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;

@Controller
public class AbstractController {

	@Autowired
	ConfigurationService	configurationService;


	// Panic handler ----------------------------------------------------------

	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(final Throwable oops) {
		ModelAndView result;

		if (!ClassUtils.getShortName(new TypeMismatchException("").getClass()).equals(ClassUtils.getShortName(oops.getClass()))) {
			result = new ModelAndView("misc/panic");
			result.addObject("name", ClassUtils.getShortName(oops.getClass()));
			result.addObject("exception", oops.getMessage());
			result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));
		} else {
			SimpleDateFormat formatter;
			String moment;

			final Map<String, String> welcomeMessages = this.configurationService.findOne().getWelcomeMessage();
			final String welcomeMessage = this.configurationService.internacionalizcion(welcomeMessages);
			final String banner = this.configurationService.findOne().getBanner();

			formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			moment = formatter.format(new Date());
			result = new ModelAndView("welcome/index");
			result.getModel().put("message", "org.hibernate.validator.constraints.URL.message");
			result.addObject("banner", banner);
			result.addObject("moment", moment);
			result.addObject("welomeMessage", welcomeMessage);
		}
		return result;
	}

}
