package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Club;
import domain.Event;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageBoxServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private BoxService boxService;

	@Autowired
	private ActorService actorService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCreate() {
		final String name = "name";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * % d) analysis of data coverage - Se crea una carpeta siendo
				 * manager1.
				 */
				{

				title, clubIdMine, "manager1", null },
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: manager can't
				 * create an event for a club who don't belongs to him. c)
				 * analysis of sentence coverage: 92.4% d) analysis of data
				 * coverage - Se intenta crear un evento en un club de manager2
				 * siendo manager1.
				 */
				{

				title, clubIdNotMine, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: manager can't
				 * create a event without a title. c) analysis of sentence
				 * coverage: 92.4% d) analysis of data coverage - Se intenta
				 * crear un evento sin título siendo manager1.
				 */
				{

				null, clubIdMine, "manager1",
						javax.validation.ConstraintViolationException.class },

				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: Only a manager
				 * can create an event. c) analysis of sentence coverage: 92.4%
				 * d) analysis of data coverage - Se intenta crear un evento
				 * siendo client1.
				 */
				{

				title, clubIdMine, "client1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreateEvent((String) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	@SuppressWarnings("deprecation")
	protected void templateCreateEvent(final String title, int clubId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Event event = this.eventService.create();
			Club club = clubService.findOne(clubId);
			event.setTitle(title);
			event.setAddress("test");
			event.setDescription("test");
			event.setAddress("test");
			event.setPrice(10.0);
			event.setClub(club);
			event.setCategory(categoryService.findRootCategory());
			event.setMomentStart(new Date(2019, 10, 21, 10, 00, 00));
			event.setMomentEnd(new Date(2019, 10, 21, 16, 00, 00));

			this.eventService.save(event);

			this.eventService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------

	@Test
	public void driverEdit() {
		final String title = "test";
		final int eventIdMine = super.getEntityId("event6");
		final int eventIdNotMine = super.getEntityId("event5");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 92.4% d) analysis of data coverage - Se edita un evento
				 * siendo manager1.
				 */
				{ title, eventIdMine, "manager1", null },
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: manager can't edit
				 * a event who don't belongs to him. c) analysis of sentence
				 * coverage: 92.4% d) analysis of data coverage - Se intenta
				 * editar un evento de manager2 siendo manager1.
				 */
				{ title, eventIdNotMine, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: manager can't
				 * edit an event without a title. c) analysis of sentence
				 * coverage: 92.4% d) analysis of data coverage - Se intenta
				 * editar un evento sin título siendo manager1.
				 */
				{ null, eventIdMine, "manager1",
						javax.validation.ConstraintViolationException.class },

				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: Only a manager
				 * can edit an event. c) analysis of sentence coverage: 92.4% d)
				 * analysis of data coverage - Se intenta crear un evento siendo
				 * client1.
				 */
				{ title, eventIdMine, "client1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEditEvent((String) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEditEvent(final String title, int eventId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Event event = this.eventService.findOne(eventId);
			event.setTitle(title);

			this.eventService.save(event);

			this.eventService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Delete--------------------------

	@Test
	public void driverDelete() {
		final int eventIdMineDraft = super.getEntityId("event6");
		final int eventIdMineNotDraft = super.getEntityId("event1");
		final int eventIdNotMineDraft = super.getEntityId("event5");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 90.1% d) analysis of data coverage - Se elimina un evento en
				 * draft mode siendo manager1.
				 */
				{ eventIdMineDraft, "manager1", null },
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: manager can't
				 * delete a event who don't belongs to him. c) analysis of
				 * sentence coverage: 90.1% d) analysis of data coverage - Se
				 * intenta eliminar un evento de manager2 siendo manager1.
				 */
				{ eventIdNotMineDraft, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.2 An actor who is
				 * authenticated as a manager must be able to: Manage the events
				 * of their clubs, which includes listing, creating, updating
				 * and deleting them. A manager may update or delete an event
				 * only if it's saved in draft mode. When an event is saved in
				 * final mode, a notification must be send at all the clients
				 * who follows the club where the event is organized.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: manager can't
				 * delete an event in final mode. c) analysis of sentence
				 * coverage: 90.1% d) analysis of data coverage - Se intenta
				 * eliminar un evento en final mode.
				 */
				{ eventIdMineNotDraft, "manager1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateDeleteEvent((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateDeleteEvent(int eventId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Event event = this.eventService.findOne(eventId);

			this.eventService.delete(event);

			this.eventService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
