package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Message;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BroadcastMessageServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverBroadcast() {
		final String body = "body";
		final String subject = "subject";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 15.4 An actor who is
				 * authenticated as an administrator must be able to: Broadcast
				 * a message to all of the actors of the system.
				 * 
				 * - BROADCAST. b) POSITIVE TEST c) analysis of sentence
				 * coverage: 96.2% d) analysis of data coverage - Se hace un
				 * broadcast siendo admin.
				 */
				{

				body, subject, "admin", null },
				/*
				 * a) Functional requirements - 15.4 An actor who is
				 * authenticated as an administrator must be able to: Broadcast
				 * a message to all of the actors of the system.
				 * 
				 * - BROADCAST. b) NEGATIVE TEST - Business rule: Only
				 * administrators can do broadcasts. c) analysis of sentence
				 * coverage: 96.2% d) analysis of data coverage - Se intenta hacer
				 * un broadcast siendo manager1.
				 */
				{ body, subject, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 15.4 An actor who is
				 * authenticated as an administrator must be able to: Broadcast
				 * a message to all of the actors of the system.
				 * 
				 * - BROADCAST. b) NEGATIVE TEST - Business rule: Broadcast messages must
				 * have a body. c) analysis of sentence coverage: 96.2% d)
				 * analysis of data coverage - Se intenta hacer un broadcast sin
				 * body siendo admin.
				 */
				{ "", subject, "admin",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateBroadcast((String) testingData[i][0],
						(String) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateBroadcast(final String body, String subject,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			Actor sender = actorService.findByUserAccount(LoginService
					.getPrincipal());

			final Message message = this.messageService.create();
			message.setBody(body);
			message.setPriority("HIGH");
			message.setSender(sender);
			message.setSubject(subject);
			message.setTags("TAG");

			this.messageService.broadcastMessage(message);

			this.messageService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
