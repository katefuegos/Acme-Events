package services;

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
public class FollowUnfollowClubServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ClubService clubService;

	// Tests ------------------------------------------------------------------

	// -------------------------Follow--------------------------
	@Test
	public void driverFollow() {
		final int clubNotFollowedId = super.getEntityId("club6");
		final int clubFollowedId = super.getEntityId("club1");
		final int clubNotFollowedNotAcceptedId = super.getEntityId("club5");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - FOLLOW. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 91.6% d) analysis of data coverage - Se sigue club siendo
				 * client1.
				 */
				{

				clubNotFollowedId, "client1", null },
				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - FOLLOW. b) NEGATIVE TEST - Business rule: Only clients can
				 * follow a club. c) analysis of sentence coverage: 91.6% d)
				 * analysis of data coverage - Se intenta seguir un club siendo
				 * manager1.
				 */
				{ clubNotFollowedId, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - FOLLOW. b) NEGATIVE TEST - Business rule: A client can not
				 * follow a club who is already followed by him. c) analysis of
				 * sentence coverage: 91.6% d) analysis of data coverage - Se
				 * intenta seguir un club que ya se sigue siendo client1.
				 */
				{ clubFollowedId, "client1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - FOLLOW. b) NEGATIVE TEST - Business rule: Only accepted
				 * clubs can be followed. c) analysis of sentence coverage:
				 * 91.6% d) analysis of data coverage - Se intenta seguir un
				 * club que ha sido aceptadosiendo client1.
				 */
				{ clubNotFollowedNotAcceptedId, "client1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateFollowClub((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateFollowClub(final int clubId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Club club = this.clubService.findOne(clubId);
			clubService.followClub(club);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Unfollow--------------------------
	@Test
	public void driverUnfollow() {
		final int clubNotFollowedId = super.getEntityId("club6");
		final int clubFollowedId = super.getEntityId("club1");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - UNFOLLOW. b) POSITIVE TEST c) analysis of sentence
				 * coverage: 91.6% d) analysis of data coverage - Se deja de
				 * seguir un club siendo client1.
				 */
				{

				clubFollowedId, "client1", null },
				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - UNFOLLOW. b) NEGATIVE TEST - Business rule: Only clients
				 * can unfollow a club. c) analysis of sentence coverage: 91.6%
				 * d) analysis of data coverage - Se intenta dejar de seguir un
				 * club siendo manager1.
				 */
				{ clubFollowedId, "manager1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 14.1. An actor who is
				 * authenticated as a client must be able to: Follow or unfollow
				 * a club.
				 * 
				 * - UNFOLLOW. b) NEGATIVE TEST - Business rule: A client can
				 * not unfollow a club that he is not following. c) analysis of
				 * sentence coverage: 91.6% d) analysis of data coverage - Se
				 * intenta dejar de seguir un club que no se está siguiendo
				 * siendo client1.
				 */
				{ clubNotFollowedId, "client1",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateUnfollowClub((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateUnfollowClub(final int clubId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Club club = this.clubService.findOne(clubId);
			clubService.unFollowClub(club);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
