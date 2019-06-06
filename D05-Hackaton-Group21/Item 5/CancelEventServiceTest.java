package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Event;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CancelEventServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private EventService eventService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCancel() {
		final int eventIdMineDraft = super.getEntityId("event6");
		final int eventIdMineNotDraft = super.getEntityId("event1");
		final int eventIdNotMineNotDraft = super.getEntityId("event2");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 13.3 An actor who is
				 * authenticated as a manager must be able to: Cancel an event.
				 * When a event is cancelled a notification must be send at all
				 * the clients who participe in this event. Only events saved in
				 * final mode can be cancelled.
				 * 
				 * - CANCEL. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 91.3% d) analysis of data coverage - Se cancela un evento
				 * siendo manager1.
				 */
				{

				eventIdMineNotDraft, "manager1", null },
				/*
				 * a) Functional requirements - 13.3 An actor who is
				 * authenticated as a manager must be able to: Cancel an event.
				 * When a event is cancelled a notification must be send at all
				 * the clients who participe in this event. Only events saved in
				 * final mode can be cancelled.
				 * 
				 * - CANCEL. b) NEGATIVE TEST - Business rule: manager can't
				 * cancel an event who don't belongs to him. c) analysis of
				 * sentence coverage: 91.3% d) analysis of data coverage - Se
				 * intenta cancelar un evento de manager2 siendo manager1.
				 */
				{

				eventIdNotMineNotDraft, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.3 An actor who is
				 * authenticated as a manager must be able to: Cancel an event.
				 * When a event is cancelled a notification must be send at all
				 * the clients who participe in this event. Only events saved in
				 * final mode can be cancelled.
				 * 
				 * - CANCEL. b) NEGATIVE TEST - Business rule: manager can't
				 * cancel an event who is in draft mode. c) analysis of sentence
				 * coverage: 91.3% d) analysis of data coverage - Se intenta
				 * cancelar un evento en draft mode siendo manager1.
				 */
				{

				eventIdMineDraft, "manager1",
						java.lang.IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCancelEvent((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCancelEvent(int eventId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			Event event = eventService.findOne(eventId);
			eventService.cancel(event);

			this.eventService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
