
package controllers.Client;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ClientService;
import services.ConfigurationService;
import services.CreditCardService;
import controllers.AbstractController;
import domain.Client;
import domain.CreditCard;
import forms.CreditCardForm;

@Controller
@RequestMapping("/creditCard/client")
public class CreditCardClientController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private ConfigurationService	configurationService;


	// Constructor---------------------------------------------------------

	public CreditCardClientController() {
		super();
	}

	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		CreditCard creditcard = null;
		try {

			final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());

			creditcard = client.getCreditCard();

			final CreditCardForm creditCardForm = this.construct(creditcard);

			result = this.createEditModelAndView(creditCardForm);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
	//
	// Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CreditCardForm creditCardForm, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(creditCardForm, "creditcard.commit.error");
		else
			try {

				final Integer d1 = Integer.valueOf(creditCardForm.getCVVCode());

				final CreditCard creditCard = this.reconstruct(creditCardForm);

				final CreditCard saved = this.creditCardService.save(creditCard);
				final CreditCardForm cardForm = this.construct(saved);

				result = this.createEditModelAndView(cardForm, "creditcard.commit.ok");

			} catch (final Throwable oops) {
				if (oops.getClass() == NumberFormatException.class)
					result = this.createEditModelAndView(creditCardForm, "creditcard.error.invalid");
				else if (oops.getMessage() == "creditcard.error.date.invalid")
					result = this.createEditModelAndView(creditCardForm, oops.getMessage());
				else
					result = this.createEditModelAndView(creditCardForm, "creditcard.commit.error");

			}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView save(@Valid final CreditCardForm creditCardForm) {

		ModelAndView result;

		try {
			Assert.isTrue(creditCardForm.getId() != 0);

			final CreditCard creditCard = this.reconstruct(creditCardForm);

			this.creditCardService.delete(creditCard);
			result = this.createEditModelAndView(new CreditCardForm(), "creditcard.commit.ok");

		} catch (final Throwable oops) {
			if (oops.getClass() == NumberFormatException.class)
				result = this.createEditModelAndView(creditCardForm, "creditcard.error.invalid");
			else if (oops.getMessage() == "creditcard.error.date.invalid")
				result = this.createEditModelAndView(creditCardForm, oops.getMessage());
			else
				result = this.createEditModelAndView(creditCardForm, "creditcard.commit.error");

		}
		return result;

	}
	// CreateModelAndView

	protected ModelAndView createEditModelAndView(final CreditCardForm creditCardForm) {
		ModelAndView result;

		result = this.createEditModelAndView(creditCardForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final CreditCardForm creditCardForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("creditcard/edit");

		final Collection<String> brandNames = new ArrayList<>();
		brandNames.add("VISA");
		brandNames.add("MASTER");
		brandNames.add("DINNERS");
		brandNames.add("AMEX");

		result.addObject("creditCardForm", creditCardForm);

		result.addObject("message", message);
		result.addObject("brandNames", brandNames);
		result.addObject("isRead", false);
		result.addObject("requestURI", "creditCard/client/edit.do");

		result.addObject("banner", this.configurationService.findAll().iterator().next().getBanner());
		result.addObject("systemName", this.configurationService.findAll().iterator().next().getSystemName());

		return result;
	}

	protected CreditCardForm construct(final CreditCard creditCard) {
		final CreditCardForm result = new CreditCardForm();
		if (creditCard == null) {
			result.setId(0);
			result.setVersion(0);

			result.setHolderName("");
			result.setBrandName("");
			result.setNumber("");
			result.setExpirationMonth("");
			result.setExpirationYear("");
			result.setCVVCode("");

		} else {
			result.setId(creditCard.getId());
			result.setVersion(creditCard.getVersion());

			result.setHolderName(creditCard.getHolderName());
			result.setBrandName(creditCard.getBrandName());
			result.setNumber(creditCard.getNumber());
			result.setExpirationMonth(String.valueOf(creditCard.getExpirationMonth()));
			result.setExpirationYear(String.valueOf(creditCard.getExpirationYear()));
			result.setCVVCode(String.valueOf(creditCard.getCVVCode()));
		}
		return result;
	}
	protected CreditCard reconstruct(final CreditCardForm creditCardForm) {
		final CreditCard result = this.creditCardService.create();

		result.setId(creditCardForm.getId());
		result.setVersion(creditCardForm.getVersion());

		result.setHolderName(creditCardForm.getHolderName());
		result.setBrandName(creditCardForm.getBrandName());
		result.setNumber(creditCardForm.getNumber());
		result.setExpirationMonth(Integer.valueOf(creditCardForm.getExpirationMonth()));
		result.setExpirationYear(Integer.valueOf(creditCardForm.getExpirationYear()));
		result.setCVVCode(Integer.valueOf(creditCardForm.getCVVCode()));

		return result;
	}

}
