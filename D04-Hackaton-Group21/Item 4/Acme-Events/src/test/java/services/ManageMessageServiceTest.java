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
import domain.Box;
import domain.Message;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageMessageServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private BoxService boxService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCreate() {
		final String body = "body";
		final String subject = "subject";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 12.4 An actor who is
				 * authenticated must be able to: Exchange messages with other
				 * actors and manage them.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 90.6% d) analysis of data coverage - Se crea un mensaje siendo
				 * manager1.
				 */
				{

				body, subject, "manager1", null },
				/*
				 * a) Functional requirements - 12.4 An actor who is
				 * authenticated must be able to: Exchange messages with other
				 * actors and manage them.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: Messages must
				 * have a body. c) analysis of sentence coverage: 90.6% d) analysis
				 * of data coverage - Se intenta crear un mensaje sin body
				 * siendo manager1.
				 */
				{ "", subject, "manager1",
						javax.validation.ConstraintViolationException.class },
				/*
				 * a) Functional requirements - 12.4 An actor who is
				 * authenticated must be able to: Exchange messages with other
				 * actors and manage them.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: Messages must
				 * have a subject. c) analysis of sentence coverage: 90.6% d)
				 * analysis of data coverage - Se intenta crear un mensaje sin
				 * subject siendo manager1.
				 */
				{ body, "", "manager1",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreate((String) testingData[i][0],
						(String) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreate(final String body, String subject,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final int receiverId = super.getEntityId("admin");
			final int boxId = super.getEntityId("inBoxA");

			Actor sender = actorService.findByUserAccount(LoginService
					.getPrincipal());
			Actor recipient = actorService.findOne(receiverId);
			Box box = boxService.findOne(boxId);

			final Message message = this.messageService.create();
			message.setBody(body);
			message.setBox(box);
			message.setPriority("HIGH");
			message.setRecipient(recipient);
			message.setSender(sender);
			message.setSubject(subject);
			message.setTags("TAG");

			this.messageService.save(message);

			this.messageService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------
	@Test
	public void driverEdit() {
		final int messageId = super.getEntityId("message1");
		final String subject = "subject";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 12.4 An actor who is
				 * authenticated must be able to: Exchange messages with other
				 * actors and manage them.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage: 91.1%
				 * d) analysis of data coverage - Se edita un mensaje siendo
				 * manager1.
				 */
				{

				messageId, subject, "manager1", null },
				/*
				 * a) Functional requirements - 12.4 An actor who is
				 * authenticated must be able to: Exchange messages with other
				 * actors and manage them.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: An actor must only
				 * edit your own messages. c) analysis of sentence coverage: 91.1%
				 * d) analysis of data coverage - Se intenta editar un mensaje
				 * ajeno siendo manager2.
				 */
				{ messageId, subject, "manager2",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 12.4 An actor who is
				 * authenticated must be able to: Exchange messages with other
				 * actors and manage them.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: Messages must have
				 * a subject. c) analysis of sentence coverage: 91.1% d) analysis of
				 * data coverage - Se intenta editar un mensaje sin subject
				 * siendo manager1.
				 */
				{ messageId, "", "manager1",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEdit((int) testingData[i][0],
						(String) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEdit(final int messageId, String subject,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final Message message = this.messageService.findOne(messageId);
			message.setSubject(subject);

			this.messageService.save(message);

			this.messageService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Delete--------------------------
	@Test
	public void driverDelete() {
		final int messageId = super.getEntityId("message1");

		final Object testingData[][] = {
		/*
		 * a) Functional requirements - 12.4 An actor who is authenticated must
		 * be able to: Exchange messages with other actors and manage them.
		 * 
		 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage: 89.3% d)
		 * analysis of data coverage - Se elimina un mensaje siendo manager1.
		 */
		{

		messageId, "manager1", null },
		/*
		 * a) Functional requirements - 12.4 An actor who is authenticated must
		 * be able to: Exchange messages with other actors and manage them.
		 * 
		 * - DELETE. b) NEGATIVE TEST - Business rule: An actor must only delete
		 * your own messages. c) analysis of sentence coverage: 89.3% d) analysis of
		 * data coverage - Se intenta eliminar un mensaje ajeno siendo manager2.
		 */
		{ messageId, "manager2", java.lang.IllegalArgumentException.class },
		/*
		 * a) Functional requirements - 12.4 An actor who is authenticated must
		 * be able to: Exchange messages with other actors and manage them.
		 * 
		 * - DELETE. b) NEGATIVE TEST - Business rule: You can't delete a
		 * message who don't exists. c) analysis of sentence coverage: 89.3% d)
		 * analysis of data coverage - Se intenta eliminar un mensaje
		 * inexistente siendo manager1.
		 */
		{ 12345, "manager1", java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateDelete((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateDelete(final int messageId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final Message message = this.messageService.findOne(messageId);

			this.messageService.delete(message);

			this.messageService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
