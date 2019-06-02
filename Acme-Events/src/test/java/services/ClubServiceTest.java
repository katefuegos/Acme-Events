
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.ApplicationClub;
import domain.Club;
import domain.Follow;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ClubServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ClubService	clubService;


	// Tests ------------------------------------------------------------------
	@Test
	public void driverCreate() {
		final String name = "test";

		final Object testingData[][] = {
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Create. b) Positive test c) analysis of sentence
			 * coverage: 88.2% d) analysis of data coverage - se crea club
			 * siendo manager1.
			 */
			{

				name, "manager1", null
			},
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * -Create. b) Negative test - Business rule: it
			 * can't be created by a not manager user c) analysis of sentence
			 * coverage: 88.2% d) analysis of data coverage - se intenta crear
			 * problem siendo client1.
			 */
			{
				name, "client1", java.lang.IllegalArgumentException.class
			},

			/*
			 * * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Create. b) Negative test - Business rule: it
			 * can't be created without a name c) analysis of sentence
			 * coverage: 88.2% d) analysis of data coverage - se intenta crear
			 * club siendo manager1 sin atributo name.
			 */
			{
				null, "manager1", javax.validation.ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreateClub((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateClub(final String name, final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Collection<ApplicationClub> applicationsClub = new ArrayList<ApplicationClub>();
			final Collection<Follow> follows = new ArrayList<Follow>();
			final Club club = this.clubService.create();
			club.setName(name);
			club.setAddress("test");
			club.setDescription("test");
			club.setPictures("http://www.test.com");
			club.setReasonReject("test");
			club.setScore(1.0);
			club.setDraftMode(true);
			club.setAccepted(true);
			club.setApplicationsClub(applicationsClub);
			club.setFollows(follows);

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
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Edit. b) Positive test c) analysis of sentence
			 * coverage: 92.4% d) analysis of data coverage - se edita club
			 * siendo manager1.
			 */
			{
				name, clubIdMine, "manager1", null
			},
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Edit. b) Negative test - Business rule: it can't
			 * be edited by a not manager user c) analysis of sentence
			 * coverage: 92.4% d) analysis of data coverage - se intenta editar
			 * club siendo client1.
			 */
			{
				name, clubIdMine, "client1", java.lang.IllegalArgumentException.class
			},
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Edit. b) Negative test - Business rule: it can't
			 * be edited by a foreign c) analysis of sentence coverage: 92.4% d)
			 * analysis of data coverage - se intenta editar club ajeno
			 * siendo manager1.
			 */
			{
				name, clubIdNotMine, "manager1", java.lang.IllegalArgumentException.class
			},
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Create. b) Negative test - Business rule: it
			 * can't be created without a name c) analysis of sentence
			 * coverage: 92.4% d) analysis of data coverage - se intenta crear
			 * club siendo manager1 sin atributo name.
			 */
			{
				null, clubIdMine, "manager1", javax.validation.ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEditClub((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEditClub(final String name, final int clubId, final String username, final Class<?> expected) {
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
		final int clubIdMine = super.getEntityId("club1");
		final int clubIdNotMine = super.getEntityId("club2");

		final Object testingData[][] = {
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Delete. b) Positive test c) analysis of sentence
			 * coverage: 89.7% d) analysis of data coverage - se elimina
			 * club siendo manager1.
			 */
			{
				clubIdMine, "manager1", null
			},
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Delete. b) Negative test - Business rule: it
			 * can't be deleted by a not client user c) analysis of
			 * sentence coverage: 89.7% d) analysis of data coverage - se
			 * intenta eliminar club siendo client1.
			 */
			{
				clubIdMine, "client1", java.lang.IllegalArgumentException.class
			},
			/*
			 * a) Functional requirements - 13. An actor who is
			 * authenticated as a manager must be able to:
			 * Manage their clubs, which includes listing, showing,
			 * creating, updating and deleting them. A club saved
			 * in final mode cannot be updated or deleted.
			 * 
			 * - Delete. b) Negative test - Business rule: it
			 * can't be deleted by a foreign client c) analysis of sentence
			 * coverage: 89.7% d) analysis of data coverage - se intenta
			 * eliminar club ajeno siendo manager1.
			 */
			{
				clubIdNotMine, "manager1", java.lang.IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateDeleteClub((int) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateDeleteClub(final int clubId, final String username, final Class<?> expected) {
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
}
