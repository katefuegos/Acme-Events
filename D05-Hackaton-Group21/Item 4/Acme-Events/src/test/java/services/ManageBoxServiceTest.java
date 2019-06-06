package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Box;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageBoxServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private BoxService boxService;
	
	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCreate() {
		final String name = "name";
		final String nameReserved = "in box";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 95% d) analysis of data coverage - Se crea una carpeta siendo
				 * manager1.
				 */
				{

				name, false, "manager1", null },
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: A box must have a
				 * name. c) analysis of sentence coverage: 95% d) analysis of data
				 * coverage - Se intenta crear una carpeta sin nombre siendo
				 * manager1.
				 */
				{ "", false, "manager1",
						javax.validation.ConstraintViolationException.class },
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: An actor can't
				 * create a box with a reserved name. c) analysis of sentence
				 * coverage: 95% d) analysis of data coverage - Se intenta crear
				 * una carpeta con un nombre reservado siendo manager1.
				 */
				{ nameReserved, false, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: An actor can't
				 * create a system box c) analysis of sentence coverage: 95%
				 * d) analysis of data coverage - Se intenta crear una carpeta
				 * de sistema siendo manager1.
				 */
				{ name, true, "manager1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreate((String) testingData[i][0],
						(boolean) testingData[i][1],
						(String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreate(final String name, boolean isSystem,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Box box = boxService.create();
			box.setName(name);
			box.setIsSystem(isSystem);

			this.boxService.save(box);

			this.boxService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------
	@Test
	public void driverEdit() {
		final String name = "name";
		final int boxIdNSMine = super.getEntityId("customBoxM1");
		final int boxIdSMine = super.getEntityId("notificationBoxM1");
		final int boxIdNSNotMine = super.getEntityId("customBoxM2");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage: 94.9%
				 * d) analysis of data coverage - Se edita el nombre de una
				 * carpeta siendo manager1.
				 */
				{

				name, boxIdNSMine, "manager1", null },
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: A box must have a
				 * name. c) analysis of sentence coverage: 94.9% d) analysis of data
				 * coverage - Se intenta editar una carpeta sin nombre siendo
				 * manager1.
				 */
				{ "", boxIdNSMine, "manager1",
						javax.validation.ConstraintViolationException.class },
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: An actor can only
				 * edit his own boxes. c) analysis of sentence coverage: 94.9% d)
				 * analysis of data coverage - Se intenta editar una carpeta
				 * ajena siendo manager1.
				 */
				{ name, boxIdNSNotMine, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: An actor can't edit
				 * a system box c) analysis of sentence coverage: 94.9% d)
				 * analysis of data coverage - Se intenta editar una carpeta de
				 * sistema siendo manager1.
				 */
				{ name, boxIdSMine, "manager1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEdit((String) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEdit(final String name, int boxId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Box box = boxService.findOne(boxId);
			box.setName(name);

			this.boxService.save(box);

			this.boxService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Delete--------------------------
	@Test
	public void driverDelete() {
		final int boxIdNSMine = super.getEntityId("customBoxM1");
		final int boxIdSMine = super.getEntityId("notificationBoxM1");
		final int boxIdNSNotMine = super.getEntityId("customBoxM2");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 94.3% d) analysis of data coverage - Se elimina una carpeta
				 * siendo manager1.
				 */
				{

				boxIdNSMine, "manager1", null },
				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: An actor can only
				 * delete his own boxes. c) analysis of sentence coverage: 94.3% d)
				 * analysis of data coverage - Se intenta eliminar una carpeta
				 * ajena siendo manager1.
				 */
				{ boxIdNSNotMine, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 12.5 An actor who is
				 * authenticated must be able to: Manage his or her messages
				 * boxes, except for the system boxes.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: An actor can't
				 * delete a system box c) analysis of sentence coverage: 94.3%
				 * d) analysis of data coverage - Se intenta eliminar una
				 * carpeta de sistema siendo manager1.
				 */
				{ boxIdSMine, "manager1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateDelete(
						(int) testingData[i][0], (String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateDelete(int boxId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Box box = boxService.findOne(boxId);

			this.boxService.delete(box);

			this.boxService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
