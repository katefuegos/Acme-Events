package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Event;
import domain.Opinion;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PublishOpinionServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private OpinionService opinionService;

	@Autowired
	private EventService eventService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverOpinion() {
		final int eventId = super.getEntityId("event2");
		final int eventIdNotFinished = super.getEntityId("event3");
		final int score = 5;
		final int badScore = 100;

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.6 An actor who is
				 * authenticated as a client must be able to: Publish an opinion
				 * about an event he participated and who is already finished.
				 * 
				 * - PUBLISH. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 94% d) analysis of data coverage - Se crea una opinion de un
				 * evento siendo client1.
				 */
				{

				eventId, score, "client1", null },
				/*
				 * a) Functional requirements - 14.6 An actor who is
				 * authenticated as a client must be able to: Publish an opinion
				 * about an event he participated and who is already finished.
				 * 
				 * - PUBLISH. b) NEGATIVE TEST - Business rule: Client can not
				 * publish an opinion about an event who have not already
				 * finished. c) analysis of sentence coverage: 94% d) analysis of
				 * data coverage - Se intenta publicar una opinión de evento que
				 * no ha terminado siendo client1.
				 */
				{

				eventIdNotFinished, score, "client1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 14.6 An actor who is
				 * authenticated as a client must be able to: Publish an opinion
				 * about an event he participated and who is already finished.
				 * 
				 * - PUBLISH. b) NEGATIVE TEST - Business rule: Only clients can
				 * publish opinions. c) analysis of sentence coverage: 94% d)
				 * analysis of data coverage - Se intenta publicar una opinión
				 * de un evento siendo manager1.
				 */
				{

				eventId, score, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.6 An actor who is
				 * authenticated as a client must be able to: Publish an opinion
				 * about an event he participated and who is already finished.
				 * 
				 * - PUBLISH. b) NEGATIVE TEST - Business rule: An opinion must
				 * have a valid score (0-10). c) analysis of sentence coverage: 94% d)
				 * analysis of data coverage - Se intenta publicar una opinion
				 * de un evento con un score inválido siendo client1.
				 */
				{ eventId, badScore, "client1",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateOpinion((int) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateOpinion(int eventId, int score,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			Event event = eventService.findOne(eventId);
			Assert.isTrue(event.getMomentEnd().before(new Date()));
			Opinion opinion = opinionService.create();
			opinion.setDescription("test");
			opinion.setTitle("test");
			opinion.setScore(score);

			opinionService.save(opinion);

			this.opinionService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
