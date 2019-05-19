
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ParticipationEventRepository;
import domain.ParticipationEvent;

@Service
@Transactional
public class ParticipationEventService {

	// Repository-----------------------------------------------

	@Autowired
	private ParticipationEventRepository			participationEventRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ParticipationEventService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public ParticipationEvent create(final String authority) {
		final ParticipationEvent participationEvent = new ParticipationEvent();
		
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
		final ParticipationEvent saved = this.participationEventRepository.save(participationEvent);
		return saved;
	}

	public void delete(final ParticipationEvent participationEvent) {
		this.participationEventRepository.delete(participationEvent);
	}

	// Other Methods--------------------------------------------
}
