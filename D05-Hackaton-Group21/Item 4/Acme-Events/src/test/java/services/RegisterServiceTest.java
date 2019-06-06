package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Administrator;
import domain.Client;
import domain.Manager;
import forms.ActorForm;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegisterServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private AdministratorService administratorService;

	// Tests ------------------------------------------------------------------

	// -------------------------Register Manager--------------------------
	@Test
	public void driverRegisterManager() {
		final String name = "name";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 11.1. An actor who is not
				 * authenticated must be able to: Register to the system as a
				 * manager or a client.
				 * 
				 * - REGISTER MANAGER. b) POSITIVE TEST c) analysis of sentence
				 * coverage: 95% d) analysis of data coverage - Se registra un
				 * nuevo manager.
				 */
				{

				true, name, null, null },
				/*
				 * a) Functional requirements - 11.1. An actor who is not
				 * authenticated must be able to: Register to the system as a
				 * manager or a client.
				 * 
				 * - REGISTER MANAGER. b) NEGATIVE TEST - Business rule: A
				 * manager must have a name. c) analysis of sentence coverage: 95%
				 * d) analysis of data coverage - Se intenta registrar un nuevo
				 * manager sin nombre.
				 */
				{ true, "", null,
						javax.validation.ConstraintViolationException.class },

				/*
				 * a) Functional requirements - 11.1. An actor who is not
				 * authenticated must be able to: Register to the system as a
				 * manager or a client.
				 * 
				 * - REGISTER MANAGER. b) NEGATIVE TEST - Business rule: To
				 * register a new manager you must accept terms and condiditions
				 * of the system. c) analysis of sentence coverage: 95% d)
				 * analysis of data coverage - Se intenta registrar un manager
				 * sin aceptar los términos y condiciones.
				 */
				{ false, name, null, java.lang.IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateRegisterManager((boolean) testingData[i][0],
						(String) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterManager(final boolean check,
			final String name, final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			Manager actor = managerService.create();
			ActorForm actorForm = new ActorForm();

			actorForm.setAddress("adress");
			actorForm.setCheckTerms(check);
			actorForm.setConfirmacion("12345");
			actorForm.setEmail("email@email.com");
			actorForm.setId(actor.getId());
			actorForm.setIsBanned(actor.getIsBanned());
			actorForm.setIsSuspicious(actor.getIsSuspicious());
			actorForm.setMiddleName("middleName");
			actorForm.setName(name);
			actorForm.setPhone("666666666");
			actorForm.setPhoto("http://www.photo.com");
			actorForm.setSurname("surname");
			actorForm.setUserAccount(actor.getUserAccount());

			actorService.update(actorForm);

			this.actorService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Register Client--------------------------
	@Test
	public void driverRegisterClient() {
		final String name = "name";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 11.1. An actor who is not
				 * authenticated must be able to: Register to the system as a
				 * manager or a client.
				 * 
				 * - REGISTER CLIENT. b) POSITIVE TEST c) analysis of sentence
				 * coverage: 95.2% d) analysis of data coverage - Se registra un
				 * nuevo cliente.
				 */
				{

				true, name, null, null },
				/*
				 * a) Functional requirements - 11.1. An actor who is not
				 * authenticated must be able to: Register to the system as a
				 * manager or a client.
				 * 
				 * - REGISTER CLIENT. b) NEGATIVE TEST - Business rule: A client
				 * must have a name. c) analysis of sentence coverage: 95.2% d)
				 * analysis of data coverage - Se intenta registrar un nuevo
				 * cliente sin nombre.
				 */
				{ true, "", null,
						javax.validation.ConstraintViolationException.class },

				/*
				 * a) Functional requirements - 11.1. An actor who is not
				 * authenticated must be able to: Register to the system as a
				 * manager or a client.
				 * 
				 * - REGISTER CLIENT. b) NEGATIVE TEST - Business rule: To
				 * register a new client you must accept terms and condiditions
				 * of the system. c) analysis of sentence coverage: 95.2% d)
				 * analysis of data coverage - Se intenta registrar un cliente
				 * sin aceptar los términos y condiciones.
				 */
				{ false, name, null, java.lang.IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateRegisterClient((boolean) testingData[i][0],
						(String) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterClient(final boolean check,
			final String name, final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			Client actor = clientService.create();
			ActorForm actorForm = new ActorForm();

			actorForm.setAddress("adress");
			actorForm.setCheckTerms(check);
			actorForm.setConfirmacion("12345");
			actorForm.setEmail("email@email.com");
			actorForm.setId(actor.getId());
			actorForm.setIsBanned(actor.getIsBanned());
			actorForm.setIsSuspicious(actor.getIsSuspicious());
			actorForm.setMiddleName("middleName");
			actorForm.setName(name);
			actorForm.setPhone("666666666");
			actorForm.setPhoto("http://www.photo.com");
			actorForm.setSurname("surname");
			actorForm.setUserAccount(actor.getUserAccount());
			actorForm.setDNI("12345678X");

			actorService.update(actorForm);

			this.actorService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Register Administrator--------------------------
	@Test
	public void driverRegisterAdministrator() {
		final String name = "name";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 15.1. An actor who is
				 * authenticated as an administrator must be able to: Create
				 * user accounts for new administrators.
				 * 
				 * - REGISTER ADMINISTRATOR. b) POSITIVE TEST c) analysis of
				 * sentence coverage: 97.5% d) analysis of data coverage - Se
				 * registra un nuevo administrador siendo admin.
				 */
				{

				true, name, "admin", null },
				/*
				 * a) Functional requirements - 15.1. An actor who is
				 * authenticated as an administrator must be able to: Create
				 * user accounts for new administrators.
				 * 
				 * - REGISTER ADMINISTRATOR. b) NEGATIVE TEST - Business rule: A
				 * administrator must have a name. c) analysis of sentence
				 * coverage: 97.5% d) analysis of data coverage - Se intenta
				 * registrar un nuevo administrador sin nombre siendo admin.
				 */
				{ true, "", "admin",
						javax.validation.ConstraintViolationException.class },

				/*
				 * a) Functional requirements - 15.1. An actor who is
				 * authenticated as an administrator must be able to: Create
				 * user accounts for new administrators.
				 * 
				 * - REGISTER ADMINISTRATOR. b) NEGATIVE TEST - Business rule:
				 * To register a new administrator you must accept terms and
				 * condiditions of the system. c) analysis of sentence coverage:
				 * 97.5% d) analysis of data coverage - Se intenta registrar un
				 * administrador sin aceptar los términos y condiciones.
				 */
				{ false, name, "admin",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 15.1. An actor who is
				 * authenticated as an administrator must be able to: Create
				 * user accounts for new administrators.
				 * 
				 * - REGISTER ADMINISTRATOR. b) NEGATIVE TEST - Business rule:
				 * Only administrators can register new administrators. c)
				 * analysis of sentence coverage: 97.5% d) analysis of data coverage
				 * - Se intenta registrar un administrador siendo client1.
				 */
				{ true, name, "client1",
						java.lang.IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateRegisterAdministrator((boolean) testingData[i][0],
						(String) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterAdministrator(final boolean check,
			final String name, final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			Administrator actor = administratorService.create();
			ActorForm actorForm = new ActorForm();

			actorForm.setAddress("adress");
			actorForm.setCheckTerms(check);
			actorForm.setConfirmacion("12345");
			actorForm.setEmail("email@email.com");
			actorForm.setId(actor.getId());
			actorForm.setIsBanned(actor.getIsBanned());
			actorForm.setIsSuspicious(actor.getIsSuspicious());
			actorForm.setMiddleName("middleName");
			actorForm.setName(name);
			actorForm.setPhone("666666666");
			actorForm.setPhoto("http://www.photo.com");
			actorForm.setSurname("surname");
			actorForm.setUserAccount(actor.getUserAccount());

			actorService.update(actorForm);

			this.actorService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
