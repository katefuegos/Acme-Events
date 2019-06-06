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
public class ParticipateEventServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private EventService eventService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverParticipate() {
		final int eventIdNotParticipated = super.getEntityId("event3");
		final int eventIdParticipated = super.getEntityId("event1");
		final int eventIdFinished = super.getEntityId("event2");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.4 An actor who is
				 * authenticated as a client must be able to: Participate in an
				 * event of one of the clubs he follows. The client must have a
				 * valid credit card linked for do this. Each client can only
				 * have one participation for event. Clients cannot participate
				 * in events who have already finished.
				 * 
				 * - PARTICIPATE. b) POSITIVE TEST c) analysis of sentence
				 * coverage: 92.3% d) analysis of data coverage - Se participa en un
				 * evento siendo client1.
				 */
				{

				eventIdNotParticipated, "client1", null },
				/*
				 * a) Functional requirements - 14.4 An actor who is
				 * authenticated as a client must be able to: Participate in an
				 * event of one of the clubs he follows. The client must have a
				 * valid credit card linked for do this. Each client can only
				 * have one participation for event. Clients cannot participate
				 * in events who have already finished.
				 * 
				 * - PARTICIPATE. b) NEGATIVE TEST - Business rule: Client can
				 * not participate in an event who have already finished. c)
				 * analysis of sentence coverage: 92.3% d) analysis of data coverage
				 * - Se intenta participar en un evento que ha terminado siendo
				 * client1.
				 */
				{

				eventIdFinished, "client1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 14.4 An actor who is
				 * authenticated as a client must be able to: Participate in an
				 * event of one of the clubs he follows. The client must have a
				 * valid credit card linked for do this. Each client can only
				 * have one participation for event. Clients cannot participate
				 * in events who have already finished.
				 * 
				 * - PARTICIPATE. b) NEGATIVE TEST - Business rule: Only clients
				 * can participate in an event. c) analysis of sentence
				 * coverage: 92.3% d) analysis of data coverage - Se intenta
				 * participar en un evento siendo manager1.
				 */
				{

				eventIdNotParticipated, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.4 An actor who is
				 * authenticated as a client must be able to: Participate in an
				 * event of one of the clubs he follows. The client must have a
				 * valid credit card linked for do this. Each client can only
				 * have one participation for event. Clients cannot participate
				 * in events who have already finished.
				 * 
				 * - PARTICIPATE. b) NEGATIVE TEST - Business rule: Clients can
				 * only participate in events saved in final mode. c) analysis
				 * of sentence coverage: 92.3% d) analysis of data coverage - Se
				 * intenta participar en un evento al que ya se participa siendo
				 * client1.
				 */
				{

				eventIdParticipated, "client1",
						java.lang.IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateParticipateEvent((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateParticipateEvent(int eventId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			Event event = eventService.findOne(eventId);
			eventService.participate(event);

			this.eventService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
