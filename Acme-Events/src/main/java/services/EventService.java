
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Client;
import domain.Club;
import domain.Event;
import domain.ParticipationEvent;
import forms.SearchForm;

@Service
@Transactional
public class EventService {

	// Repository-----------------------------------------------

	@Autowired
	private EventRepository	eventRepository;

	// Services-------------------------------------------------
	@Autowired
	private ClientService	clientService;


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
	
	public Event participate(final Event event) {
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

	public Collection<Event> findEventsByFollower(final Client c) {

		return this.eventRepository.findEventsByFollower(c.getId());
	}

	public Collection<Event> findEventsByFollowerAndClub(final Client c, final Club club) {

		return this.eventRepository.findEventsByFollowerAndClub(c.getId(), club.getId());
	}

	private SearchForm checkSearch(final SearchForm f) {
		SearchForm result;

		final Date currentDate = new Date();

		if (f.getKeyWord() == null)
			f.setKeyWord("");

		if (f.getPriceMin() == null)
			f.setPriceMin(0.0);

		if (f.getPriceMax() == null)
			f.setPriceMax(99999999.9);

		if (f.getDateMin() == null)
			f.setDateMin(currentDate);

		if (f.getDateMax() == null)
			f.setDateMax(new Date(currentDate.getTime() + 315360000000L * 2));// 315360000000L son 10 años en milisegundos

		result = f;

		return result;
	}

	public Collection<domain.Event> searchPosition(final SearchForm f) {

		final SearchForm search = this.checkSearch(f);
		final String langCategory = LocaleContextHolder.getLocale().getLanguage().toUpperCase();

		final Collection<Event> result = this.eventRepository.searchEvent(search.getKeyWord(), langCategory, search.getDateMin(), search.getDateMax(), search.getPriceMin(), search.getPriceMax());

		return result;
	}
	
	public Event findByParticipationForm(ParticipationEvent participation){
		Assert.notNull(participation);
		return eventRepository.findByParticipationForm(participation);
	}

	public Collection<Event> findOpinionable(final Client client) {
		Collection<Event> result;

		Assert.notNull(client, "opinion.client.null");

		result = this.eventRepository.findByParticipation(client.getId());

		final Collection<Event> result2 = this.eventRepository.findByOpinion(client.getId());

		result.removeAll(result2);

		return result;
	}

}
