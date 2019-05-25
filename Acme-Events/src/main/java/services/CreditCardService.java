
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.LoginService;
import domain.Client;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Repository-----------------------------------------------

	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Services-------------------------------------------------
	@Autowired
	private ClientService			clientService;


	// Constructor----------------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public CreditCard create() {
		final CreditCard creditCard = new CreditCard();

		return creditCard;
	}

	public List<CreditCard> findAll() {
		return this.creditCardRepository.findAll();
	}

	public CreditCard findOne(final Integer creditCardId) {
		return this.creditCardRepository.findOne(creditCardId);
	}

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		if (creditCard.getId() != 0)
			this.checkPrincipal(creditCard);

		final Date currentDate = new Date();
		@SuppressWarnings("deprecation")
		final int month = currentDate.getMonth() + 1;
		@SuppressWarnings("deprecation")
		final int year = currentDate.getYear() + 1900;

		@SuppressWarnings("unused")
		final boolean b = (creditCard.getExpirationYear() > year) || (creditCard.getExpirationYear() == year && creditCard.getExpirationMonth() > month);
		Assert.isTrue((creditCard.getExpirationYear() > year) || (creditCard.getExpirationYear() == year && creditCard.getExpirationMonth() > month), "actor.creditcard.error.date.invalid");

		final CreditCard saved = this.creditCardRepository.save(creditCard);
		if (creditCard.getId() == 0) {
			final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
			Assert.notNull(client, "creditcard.error.notClient");
			client.setCreditCard(saved);
			this.clientService.save(client);
		}

		return saved;
	}
	public void delete(final CreditCard creditCard) {
		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());

		this.checkPrincipal(creditCard);

		client.setCreditCard(null);
		this.clientService.save(client);

		this.creditCardRepository.delete(creditCard);

	}

	public void checkPrincipal(final CreditCard creditCard) {
		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
		Assert.notNull(client, "creditcard.error.notClient");

		Assert.isTrue(client.getCreditCard().getId() == creditCard.getId(), "creditCard.notOwner");

	}

	// Other Methods--------------------------------------------

}
