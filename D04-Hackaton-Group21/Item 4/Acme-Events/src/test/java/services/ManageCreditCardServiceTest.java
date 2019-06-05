package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CreditCard;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageCreditCardServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private CreditCardService creditCardService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCreate() {
		final String brandName = "VISA";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 96.2% d) analysis of data coverage - Se crea una tarjeta de
				 * crédito siendo client2.
				 */
				{

				brandName, "client2", null },
				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * -CREATE. b) NEGATIVE TEST - Business rule: Only clients can
				 * create credit cards.c) analysis of sentence coverage: 96.2%
				 * d) analysis of data coverage - Se intenta crear una tarjeta
				 * de crédito siendo manager1.
				 */
				{ brandName, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: A credit card
				 * must have a brand name. c) analysis of sentence coverage:
				 * 96.2% d) analysis of data coverage - se intenta crear una
				 * tarjeta de crédito sin brandName siendo client2.
				 */
				{ null, "client2",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreate((String) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreate(final String brandName,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final CreditCard creditCard = creditCardService.create();
			creditCard.setBrandName(brandName);
			creditCard.setCVVCode(123);
			creditCard.setExpirationMonth(12);
			creditCard.setExpirationYear(2019);
			creditCard.setHolderName("TEST");
			creditCard.setNumber("4463747580224911");

			this.creditCardService.save(creditCard);

			this.creditCardService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------
	@Test
	public void driverEdit() {
		final String brandName = "VISA";
		final String brandNameBad = "VISAXX";
		final int creditCardId = super.getEntityId("creditCard1");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage: 94.9%
				 * d) analysis of data coverage - Se edita una tarjeta de
				 * crédito siendo client1.
				 */
				{

				creditCardId, brandName, "client1", null },
				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * -EDIT. b) NEGATIVE TEST - Business rule: Only clients can
				 * edit credit cards.c) analysis of sentence coverage: 94.9% d)
				 * analysis of data coverage - Se intenta editar una tarjeta de
				 * crédito siendo manager1.
				 */
				{ creditCardId, brandName, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: A credit card must
				 * have a valid brand name. c) analysis of sentence coverage:
				 * 94.9% d) analysis of data coverage - se intenta editar una
				 * tarjeta de crédito con un brandName no válido siendo client1.
				 */
				{ creditCardId, brandNameBad, "client1",
						javax.validation.ConstraintViolationException.class },

				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: A client can only
				 * edit his own credit cards. c) analysis of sentence coverage:
				 * 94.9% d) analysis of data coverage - se intenta editar una
				 * tarjeta de crédito ajena siendo client2.
				 */
				{ creditCardId, brandName, "client2",
						java.lang.IllegalArgumentException.class } };

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

	protected void templateEdit(final int creditCardId, final String brandName,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final CreditCard creditCard = creditCardService
					.findOne(creditCardId);
			creditCard.setBrandName(brandName);

			this.creditCardService.save(creditCard);

			this.creditCardService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Delete--------------------------
	@Test
	public void driverDelete() {
		final int creditCardId = super.getEntityId("creditCard1");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage: 94.3%
				 * d) analysis of data coverage - Se elimina una tarjeta de
				 * crédito siendo client1.
				 */
				{

				creditCardId, "client1", null },
				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: Only clients can
				 * delete credit cards.c) analysis of sentence coverage: 94.3% d)
				 * analysis of data coverage - Se intenta eliminar una tarjeta de
				 * crédito siendo manager1.
				 */
				{ creditCardId, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.7. An actor who is
				 * authenticated as a client must be able to: Manage his credit
				 * card, who includes creating, editing and deleting it.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: A client can only
				 * delete his own credit cards. c) analysis of sentence coverage:
				 * 94.3% d) analysis of data coverage - se intenta elimnar una
				 * tarjeta de crédito ajena siendo client2.
				 */
				{ creditCardId, "client2",
						java.lang.IllegalArgumentException.class } };

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

	protected void templateDelete(final int creditCardId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final CreditCard creditCard = creditCardService
					.findOne(creditCardId);

			this.creditCardService.delete(creditCard);

			this.creditCardService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
