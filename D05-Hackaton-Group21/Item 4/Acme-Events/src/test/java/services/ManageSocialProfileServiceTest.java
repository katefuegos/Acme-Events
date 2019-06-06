package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;
import utilities.AbstractTest;
import domain.SocialProfile;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageSocialProfileServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private SocialProfileService socialProfileService;

	@Autowired
	private ActorService actorService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@Test
	public void driverCreate() {
		final String nick = "test";
		final String link = "http://www.link.com";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 95.9% d) analysis of data coverage - Se crea un perfil social
				 * siendo manager1.
				 */
				{

				nick, link, "manager1", null },
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: A social profile
				 * must have a name. c) analysis of sentence coverage: 95.9% d)
				 * analysis of data coverage - Se intenta crear un perfil social
				 * sin nick siendo manager1.
				 */
				{

				null, link, "manager1",
						javax.validation.ConstraintViolationException.class },
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: A social profile
				 * must have a link. c) analysis of sentence coverage: 95.9% d)
				 * analysis of data coverage - Se intenta crear un perfil social
				 * sin link siendo manager1.
				 */
				{

				nick, null, "manager1",
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

	protected void templateCreate(final String nick, String link,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final SocialProfile socialProfile = socialProfileService.create();

			socialProfile.setLink(link);
			socialProfile.setName("name");
			socialProfile.setNick(nick);
			socialProfile.setActor(actorService.findByUserAccount(LoginService
					.getPrincipal()));

			socialProfileService.save(socialProfile);

			socialProfileService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------
	@Test
	public void driverEdit() {
		final String nick = "test";
		final int socialProfileId = super.getEntityId("socialProfile2");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage: 94.9%
				 * d) analysis of data coverage - Se edita el nick de un perfil
				 * social siendo manager1.
				 */
				{

				nick, socialProfileId, "manager1", null },
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: An actor can only
				 * edit his own social profiles. c) analysis of sentence
				 * coverage: 94.9% d) analysis of data coverage - Se intenta editar
				 * un perfil social ajeno siendo manager2.
				 */
				{

				nick, socialProfileId, "manager2",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: A social profile
				 * must have a nick. c) analysis of sentence coverage: 94.9% d)
				 * analysis of data coverage - Se intenta editar un perfil
				 * social sin nick siendo manager1.
				 */
				{

				null, socialProfileId, "manager1",
						javax.validation.ConstraintViolationException.class } };

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

	protected void templateEdit(final String nick, int socialProfileId,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final SocialProfile socialProfile = socialProfileService
					.findOne(socialProfileId);

			socialProfile.setNick(nick);

			socialProfileService.save(socialProfile);

			socialProfileService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------
	@Test
	public void driverDelete() {
		final int socialProfileId = super.getEntityId("socialProfile2");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 94.3% d) analysis of data coverage - Se elimina un perfil social
				 * siendo manager1.
				 */
				{

				socialProfileId, "manager1", null },
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: An actor can only
				 * delete his own social profiles. c) analysis of sentence
				 * coverage: 94.3% d) analysis of data coverage - Se intenta
				 * eliminar un perfil social ajeno siendo manager2.
				 */
				{ socialProfileId, "manager2",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 11.3 An actor who is
				 * authenticated must be able to: Manage his or her social
				 * profiles, who include creating, updating and deleting them.
				 * 
				 * - DELETE. b) NEGATIVE TEST - Business rule: You can't delete
				 * a social profile who don't exists. c) analysis of sentence
				 * coverage: 94.3% d) analysis of data coverage - Se intenta
				 * eliminar un perfil social inexistente.
				 */
				{

				12345, "manager1", java.lang.IllegalArgumentException.class } };

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

	protected void templateDelete(int socialProfileId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final SocialProfile socialProfile = socialProfileService
					.findOne(socialProfileId);

			socialProfileService.delete(socialProfile);

			socialProfileService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
