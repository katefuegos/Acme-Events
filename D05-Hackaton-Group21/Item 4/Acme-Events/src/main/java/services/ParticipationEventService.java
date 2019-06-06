
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ParticipationEventRepository;
import security.LoginService;
import domain.Client;
import domain.CreditCard;
import domain.ParticipationEvent;

@Service
@Transactional
public class ParticipationEventService {

	// Repository-----------------------------------------------

	@Autowired
	private ParticipationEventRepository			participationEventRepository;

	// Services-------------------------------------------------
	
	@Autowired
	private ClientService			clientService;

	// Constructor----------------------------------------------

	public ParticipationEventService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public ParticipationEvent create() {
		final ParticipationEvent participationEvent = new ParticipationEvent();
		
		Client client = clientService.findClientByUseraccount(LoginService.getPrincipal());
		Assert.notNull(client);
		CreditCard creditCard = client.getCreditCard();
		Assert.notNull(creditCard);
		participationEvent.setCreditCardNumber(creditCard.getNumber());
		participationEvent.setClient(client);
		
		return participationEvent;
	}

	public List<ParticipationEvent> findAll() {
		return this.participationEventRepository.findAll();
	}

	public ParticipationEvent findOne(final Integer participationEventId) {
		return this.participationEventRepository.findOne(participationEventId);
	}

	public ParticipationEvent save(final ParticipationEvent participationEvent) {
		Assert.notNull(participationEvent);
		participationEvent.setMoment(new Date(System.currentTimeMillis() - 1000));
		final ParticipationEvent saved = this.participationEventRepository.save(participationEvent);
		return saved;
	}

	public void delete(final ParticipationEvent participationEvent) {
		this.participationEventRepository.delete(participationEvent);
	}

	// Other Methods--------------------------------------------
	
	public Collection<ParticipationEvent> findByClientId(int clientId){
		Assert.notNull(clientId);
		return participationEventRepository.findByClientId(clientId);
	}
}
