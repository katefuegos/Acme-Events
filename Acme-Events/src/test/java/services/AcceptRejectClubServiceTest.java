package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Club;
import forms.ClubForm;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AcceptRejectClubServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ClubService clubService;

	// Tests ------------------------------------------------------------------

	// -------------------------Accept--------------------------
	@Test
	public void driverAccept() {
		final int clubFinalId = super.getEntityId("club7");
		final int clubDraftId = super.getEntityId("club4");
		final int clubAcceptedId = super.getEntityId("club1");

		final Object testingData[][] = {
		/*
		 * a) Functional requirements - 15.3. An actor who is authenticated as a
		 * administrator must be able to: List the clubs that are not accepted
		 * yet and accept or refuse them. If the club is refused, a reason must
		 * be given by the administrator.
		 * 
		 * - ACCEPT. b) POSITIVE TEST c) analysis of sentence coverage: 94.3% d)
		 * analysis of data coverage - Se acepta un club siendo admin.
		 */
		{

		clubFinalId, "admin", null },
		/*
		 * a) Functional requirements - 15.3. An actor who is authenticated as a
		 * administrator must be able to: List the clubs that are not accepted
		 * yet and accept or refuse them. If the club is refused, a reason must
		 * be given by the administrator.
		 * 
		 * - ACCEPT. b) NEGATIVE TEST - Business rule: Only clubs in final mode
		 * can be accepted. c) analysis of sentence coverage: 94.3% d) analysis of
		 * data coverage - Se intenta aceptar un club que está en draft mode
		 * siendo admin.
		 */
		{ clubDraftId, "admin", java.lang.IllegalArgumentException.class },

		/*
		 * a) Functional requirements - 15.3. An actor who is authenticated as a
		 * administrator must be able to: List the clubs that are not accepted
		 * yet and accept or refuse them. If the club is refused, a reason must
		 * be given by the administrator.
		 * 
		 * - ACCEPT. b) NEGATIVE TEST - Business rule: Only administrators can
		 * accept a club. c) analysis of sentence coverage: 94.3% d) analysis of
		 * data coverage - Se intenta aceptar un club siendo client1.
		 */
		{ clubFinalId, "client1", java.lang.IllegalArgumentException.class },

		/*
		 * a) Functional requirements - 15.3. An actor who is authenticated as a
		 * administrator must be able to: List the clubs that are not accepted
		 * yet and accept or refuse them. If the club is refused, a reason must
		 * be given by the administrator.
		 * 
		 * - ACCEPT. b) NEGATIVE TEST - Business rule: Only clubs in pending
		 * status can be accepted, c) analysis of sentence coverage: 94.3% d)
		 * analysis of data coverage - Se intenta aceptar un club que ya ha sido
		 * aceptado siendo admin.
		 */
		{ clubAcceptedId, "admin", java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateAccept((int) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateAccept(final int clubId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Club club = this.clubService.findOne(clubId);
			clubService.accept(club);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Reject--------------------------
	@Test
	public void driverReject() {
		final int clubFinalId = super.getEntityId("club7");
		final int clubDraftId = super.getEntityId("club4");
		final int clubAcceptedId = super.getEntityId("club1");
		final String reason = "reason";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 15.3. An actor who is
				 * authenticated as a administrator must be able to: List the
				 * clubs that are not accepted yet and accept or refuse them. If
				 * the club is refused, a reason must be given by the
				 * administrator.
				 * 
				 * - REJECT. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 93.6% d) analysis of data coverage - Se rechaza un club siendo
				 * admin.
				 */
				{

				reason, clubFinalId, "admin", null },
				/*
				 * a) Functional requirements - 15.3. An actor who is
				 * authenticated as a administrator must be able to: List the
				 * clubs that are not accepted yet and accept or refuse them. If
				 * the club is refused, a reason must be given by the
				 * administrator.
				 * 
				 * - REJECT. b) NEGATIVE TEST - Business rule: Only clubs in
				 * final mode can be rejected. c) analysis of sentence coverage:
				 * 93.6% d) analysis of data coverage - Se intenta rechazar un club
				 * que está en draft mode siendo admin.
				 */
				{ reason, clubDraftId, "admin",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 15.3. An actor who is
				 * authenticated as a administrator must be able to: List the
				 * clubs that are not accepted yet and accept or refuse them. If
				 * the club is refused, a reason must be given by the
				 * administrator.
				 * 
				 * - REJECT. b) NEGATIVE TEST - Business rule: Only
				 * administrators can reject a club. c) analysis of sentence
				 * coverage: 93.6% d) analysis of data coverage - Se intenta
				 * rechazar un club siendo client1.
				 */
				{ reason, clubFinalId, "client1",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 15.3. An actor who is
				 * authenticated as a administrator must be able to: List the
				 * clubs that are not accepted yet and accept or refuse them. If
				 * the club is refused, a reason must be given by the
				 * administrator.
				 * 
				 * - REJECT. b) NEGATIVE TEST - Business rule: Only clubs in
				 * pending status can be rejected. c) analysis of sentence
				 * coverage: 93.6% d) analysis of data coverage - Se intenta
				 * rechazar un club que ya ha sido aceptado siendo admin.
				 */
				{ reason, clubAcceptedId, "admin",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements - 15.3. An actor who is
				 * authenticated as a administrator must be able to: List the
				 * clubs that are not accepted yet and accept or refuse them. If
				 * the club is refused, a reason must be given by the
				 * administrator.
				 * 
				 * - REJECT. b) NEGATIVE TEST - Business rule: A reason must be
				 * done when a club is rejected. c) analysis of sentence
				 * coverage: 93.6% d) analysis of data coverage - Se intenta
				 * rechazar un club sin dar razones.
				 */
				{ null, clubFinalId, "admin",
						java.lang.IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateReject((String) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateReject(final String reason, final int clubId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			ClubForm clubForm = new ClubForm();
			clubForm.setId(clubId);
			clubForm.setReasonReject(reason);

			clubService.reject(clubForm);

			this.clubService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
