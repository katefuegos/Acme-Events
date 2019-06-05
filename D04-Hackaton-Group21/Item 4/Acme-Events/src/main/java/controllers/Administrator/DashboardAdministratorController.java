
package controllers.Administrator;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ConfigurationService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor-------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	// Dashboard---------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView modelAndView = new ModelAndView("administrator/dashboard");
		final DecimalFormat df = new DecimalFormat("0.00");

		final String nulo = "n/a";

		// ------------
		// QueryC1
		final Object[] result = this.administratorService.queryC1();

		final Double avgC1 = (Double) result[0];
		final Double minC1 = (Double) result[1];
		final Double maxC1 = (Double) result[2];
		final Double stddevC1 = (Double) result[3];

		if (avgC1 != null)
			modelAndView.addObject("avgC1", df.format(avgC1));
		else
			modelAndView.addObject("avgC1", nulo);

		if (maxC1 != null)
			modelAndView.addObject("maxC1", df.format(maxC1));
		else
			modelAndView.addObject("maxC1", nulo);

		if (minC1 != null)
			modelAndView.addObject("minC1", df.format(minC1));
		else
			modelAndView.addObject("minC1", nulo);

		if (stddevC1 != null)
			modelAndView.addObject("stddevC1", df.format(stddevC1));
		else
			modelAndView.addObject("stddevC1", nulo);

		// QueryC2
		final Object[] resultC2 = this.administratorService.queryC2();

		final Double avgC2 = (Double) resultC2[0];
		final Double minC2 = (Double) resultC2[1];
		final Double maxC2 = (Double) resultC2[2];
		final Double stddevC2 = (Double) resultC2[3];

		if (avgC2 != null)
			modelAndView.addObject("avgC2", df.format(avgC2));
		else
			modelAndView.addObject("avgC2", nulo);

		if (maxC2 != null)
			modelAndView.addObject("maxC2", df.format(maxC2));
		else
			modelAndView.addObject("maxC2", nulo);

		if (minC2 != null)
			modelAndView.addObject("minC2", df.format(minC2));
		else
			modelAndView.addObject("minC2", nulo);

		if (stddevC2 != null)
			modelAndView.addObject("stddevC2", df.format(stddevC2));
		else
			modelAndView.addObject("stddevC2", nulo);

		//QueryC3 - 
		final Object[] resultC3 = this.administratorService.queryC3();

		final Double avgC3 = (Double) resultC3[0];
		final Double minC3 = (Double) resultC3[1];
		final Double maxC3 = (Double) resultC3[2];
		final Double stddevC3 = (Double) resultC3[3];

		if (avgC3 != null)
			modelAndView.addObject("avgC3", df.format(avgC3));
		else
			modelAndView.addObject("avgC3", nulo);

		if (maxC3 != null)
			modelAndView.addObject("maxC3", df.format(maxC3));
		else
			modelAndView.addObject("maxC3", nulo);

		if (minC3 != null)
			modelAndView.addObject("minC3", df.format(minC3));
		else
			modelAndView.addObject("minC3", nulo);

		if (stddevC3 != null)
			modelAndView.addObject("stddevC3", df.format(stddevC3));
		else
			modelAndView.addObject("stddevC3", nulo);

		//QueryC4 - 

		final Object[] resultC4 = this.administratorService.queryC4();

		final Double avgC4 = (Double) resultC4[0];
		final Double minC4 = (Double) resultC4[1];
		final Double maxC4 = (Double) resultC4[2];
		final Double stddevC4 = (Double) resultC4[3];

		if (avgC4 != null)
			modelAndView.addObject("avgC4", df.format(avgC4));
		else
			modelAndView.addObject("avgC4", nulo);

		if (maxC4 != null)
			modelAndView.addObject("maxC4", df.format(maxC4));
		else
			modelAndView.addObject("maxC4", nulo);

		if (minC4 != null)
			modelAndView.addObject("minC4", df.format(minC4));
		else
			modelAndView.addObject("minC4", nulo);

		if (stddevC4 != null)
			modelAndView.addObject("stddevC4", df.format(stddevC4));
		else
			modelAndView.addObject("stddevC4", nulo);

		// QueryC5
		final Double resultC5 = this.administratorService.queryC5();

		if (resultC5 != null)
			modelAndView.addObject("resultC5", df.format(resultC5));
		else
			modelAndView.addObject("resultC5", nulo);

		//QueryC6 - 
		final Double resultC6 = this.administratorService.queryC6();

		if (resultC6 != null)
			modelAndView.addObject("resultC6", df.format(resultC6));
		else
			modelAndView.addObject("resultC6", nulo);

		//QueryC7 - 
		modelAndView.addObject("queryC7", this.administratorService.queryC7());

		// --------------------------------
		modelAndView.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		modelAndView.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());
		return modelAndView;
	}
}
