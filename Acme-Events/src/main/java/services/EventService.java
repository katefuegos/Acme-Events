
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Client;
import domain.Club;
import domain.Event;
import domain.Manager;
import domain.Opinion;
import domain.ParticipationEvent;
import forms.SearchForm;

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

	public Event create() {
		final Event event = new Event();
		event.setTicker(this.generateTicker());
		event.setStatus("AVAILABLE");
		event.setDraftMode(true);
		event.setParticipationsEvent(new ArrayList<ParticipationEvent>());
		event.setOpinions(new ArrayList<Opinion>());
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
		if (event.isDraftMode() == false && event.getMomentPublished() == null)
			event.setMomentPublished(new Date(System.currentTimeMillis() - 1000));
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

	public void cancel(final Event event) {
		Assert.notNull(event);
		Assert.isTrue(event.getStatus().equals("AVAILABLE"));
		event.setStatus("CANCELLED");
		this.eventRepository.save(event);
	}
	
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

	public Collection<domain.Event> searchEvent(final SearchForm f) {

		final SearchForm search = this.checkSearch(f);
		final String langCategory = LocaleContextHolder.getLocale().getLanguage().toUpperCase();

		final Collection<Event> result = this.eventRepository.searchEvent(search.getKeyWord(), langCategory, search.getDateMin(), search.getDateMax(), search.getPriceMin(), search.getPriceMax());

		return result;
	}

	public Event findByParticipationForm(final ParticipationEvent participation) {
		Assert.notNull(participation);
		return this.eventRepository.findByParticipationForm(participation);
	}

	public Collection<Event> findOpinionable(final Client client) {
		Collection<Event> result;

		Assert.notNull(client, "opinion.client.null");

		result = this.eventRepository.findByParticipationAndFinalize(client.getId());

		final Collection<Event> result2 = this.eventRepository.findByOpinion(client.getId());

		result.removeAll(result2);

		return result;
	}

	public Event findByOpinionForm(final Opinion opinion) {
		Assert.notNull(opinion);
		return this.eventRepository.findByOpinionForm(opinion);
	}

	public Collection<Event> findEventsByClub(final int clubId) {
		Collection<Event> result;

		Assert.notNull(clubId);

		result = this.eventRepository.findEventsByClub(clubId);

		return result;
	}

	public Collection<Event> findByManager(final Manager manager) {
		Assert.notNull(manager);
		return this.eventRepository.findByManager(manager.getId());

	}

	public Collection<Event> findByManagerAndDraft(final Manager manager) {
		Assert.notNull(manager);
		return this.eventRepository.findByManagerAndDraft(manager.getId());

	}

	public Collection<Event> findByManagerAndFinal(final Manager manager) {
		Assert.notNull(manager);
		return this.eventRepository.findByManagerAndFinal(manager.getId());

	}

	public Collection<Event> findFinalModel(final int clubId) {
		Collection<Event> result;

		Assert.notNull(clubId);

		result = this.eventRepository.findFinalModelAndClub(clubId);

		return result;
	}

	public Collection<Event> findFinalModel() {
		Collection<Event> result;
		result = this.eventRepository.findFinalModel();

		return result;
	}

	@SuppressWarnings("deprecation")
	public String generateTicker() {
		final Date date = new Date();
		final Integer s1 = date.getDate();
		String day = s1.toString();
		if (day.length() == 1)
			day = "0" + day;
		final Integer s2 = date.getMonth() + 1;
		String month = s2.toString();
		if (month.length() == 1)
			month = "0" + month;
		final Integer s3 = date.getYear();
		final String year = s3.toString().substring(1);

		return year + month + day + "-" + this.generateStringAux();
	}

	private String generateStringAux() {
		final int length = 6;
		final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		final Random rng = new Random();
		final char[] text = new char[length];
		for (int i = 0; i < 6; i++)
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		return new String(text);
	}

}
