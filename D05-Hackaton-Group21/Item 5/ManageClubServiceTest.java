package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Club;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageClubServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ClubService clubService;

	@Autowired
	private ActorService actorService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCreate() {
		final String name = "test";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 88.2% d) analysis of data coverage - Se crea club siendo
				 * manager1.
				 */
				{

				name, "manager1", null },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * -CREATE. b) NEGATIVE TEST - Business rule: it can't be
				 * created by a not manager user c) analysis of sentence
				 * coverage: 88.2% d) analysis of data coverage - Se intenta
				 * crear problem siendo client1.
				 */
				{ name, "client1", java.lang.IllegalArgumentException.class },

				/*
				 * * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: it can't be
				 * created without a name c) analysis of sentence coverage:
				 * 88.2% d) analysis of data coverage - se intenta crear club
				 * siendo manager1 sin atributo name.
				 */
				{ null, "manager1",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreateClub((String) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateClub(final String name, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Club club = this.clubService.create();
			club.setName(name);
			club.setAddress("test");
			club.setDescription("test");
			club.setPictures("http://www.test.com");

			this.clubService.save(club);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------

	@Test
	public void driverEdit() {
		final int clubIdMine = super.getEntityId("club1");
		final int clubIdNotMine = super.getEntityId("club2");
		final String name = "test";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 92.4% d) analysis of data coverage - se edita club siendo
				 * manager1.
				 */
				{ name, clubIdMine, "manager1", null },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: it can't be edited
				 * by a not manager user c) analysis of sentence coverage: 92.4%
				 * d) analysis of data coverage - se intenta editar club siendo
				 * client1.
				 */
				{ name, clubIdMine, "client1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: it can't be edited
				 * by a foreign c) analysis of sentence coverage: 92.4% d)
				 * analysis of data coverage - se intenta editar club ajeno
				 * siendo manager1.
				 */
				{ name, clubIdNotMine, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: it can't be edited
				 * without a name c) analysis of sentence coverage: 92.4% d)
				 * analysis of data coverage - se intenta editar club siendo
				 * manager1 sin atributo name.
				 */
				{ null, clubIdMine, "manager1",
						javax.validation.ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEditClub((String) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEditClub(final String name, final int clubId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final Club club = this.clubService.findOne(clubId);
			club.setName(name);
			this.clubService.save(club);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// ------------------------------Delete----------------------------------

	@Test
	public void driverDelete() {
		final int clubIdMineDraft = super.getEntityId("club4");
		final int clubIdMineNotDraft = super.getEntityId("club1");
		final int clubIdNotMineDraft = super.getEntityId("club5");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 91.5% d) analysis of data coverage - se elimina club que está
				 * en draft mode siendo manager1.
				 */
				{ clubIdMineDraft, "manager1", null },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: it can't be
				 * deleted by a not client user c) analysis of sentence
				 * coverage: 91.5% d) analysis of data coverage - se intenta
				 * eliminar club siendo client1.
				 */
				{ clubIdMineDraft, "client1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: it can't be
				 * deleted by a foreign client c) analysis of sentence coverage:
				 * 91.5% d) analysis of data coverage - se intenta eliminar club
				 * ajeno siendo manager1.
				 */
				{ clubIdNotMineDraft, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 13.1. An actor who is
				 * authenticated as a manager must be able to: Manage their
				 * clubs, which includes listing, showing, creating, updating
				 * and deleting them. A club saved in final mode cannot be
				 * updated or deleted.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: it can't be
				 * deleted by a foreign client c) analysis of sentence coverage:
				 * 91.5% d) analysis of data coverage - se intenta eliminar un
				 * club en final mode siendo manager1.
				 */
				{ clubIdMineNotDraft, "manager1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateDeleteClub((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateDeleteClub(final int clubId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			final Club club = this.clubService.findOne(clubId);
			this.clubService.delete(club);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// ------------------------------List----------------------------------
	@Test
	public void driverList() {

		final Object testingData[][] = {
		/*
		 * a) Functional requirements - 13.1. An actor who is authenticated as a
		 * manager must be able to: Manage their clubs, which includes listing,
		 * showing, creating, updating and deleting them. A club saved in final
		 * mode cannot be updated or deleted.
		 * 
		 * - LIST. b) POSITIVE TEST c) analysis of sentence coverage: 82.8% d)
		 * analysis of data coverage - se lista "mis clubes" siendo manager1.
		 */
		{ "manager1", null },
		/*
		 * a) Functional requirements - 13.1. An actor who is authenticated as a
		 * manager must be able to: Manage their clubs, which includes listing,
		 * showing, creating, updating and deleting them. A club saved in final
		 * mode cannot be updated or deleted.
		 * 
		 * - LIST. b) NEGATIVE TEST - Business rule: it can't be deleted by a
		 * not client user c) analysis of sentence coverage: 82.8% d) analysis
		 * of data coverage - se lista "mis clubes" siendo client1.
		 */
		{ "client1", java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateListClub((String) testingData[i][0],
						(Class<?>) testingData[i][1]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateListClub(final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			@SuppressWarnings("unused")
			final Collection<Club> clubs = this.clubService
					.findByManagerId(actorService.findActorByUsername(username)
							.getId());

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
