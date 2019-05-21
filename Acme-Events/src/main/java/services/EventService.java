
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Event;

@Service
@Transactional
public class EventService {

	// Repository-----------------------------------------------

	@Autowired
	private EventRepository	eventRepository;


	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public EventService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Event create(final String authority) {
		final Event event = new Event();

		return event;
	}

	public List<Event> findAll() {
		return this.eventRepository.findAll();
	}

	public Event findOne(final Integer eventId) {
		return this.eventRepository.findOne(eventId);
	}

	public Event save(final Event event) {
		Assert.notNull(event);
		final Event saved = this.eventRepository.save(event);
		return saved;
	}

	public void delete(final Event event) {
		this.eventRepository.delete(event);
	}

	// Other Methods--------------------------------------------

	public Collection<Event> findEventsByCategoryId(final int categoryId) {
		final Collection<Event> events = this.eventRepository.findEventByCategoryId(categoryId);
		return events;
	}
}
