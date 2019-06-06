package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Client;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EditPersonalDataServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ClientService clientService;

	// Tests ------------------------------------------------------------------

	@Test
	public void driverEditPersonalData() {

		final Integer clientId = this.getEntityId("client1");
		final String name = "name";

		final Object testingData[][] = {
				/*
				 * a) Functional requirements 11.2 - An actor who is
				 * authenticated must be able to: Edit his or her personal data.
				 * EDIT b)POSITIVE TEST c) analysis of sentence coverage: 91.8% d)
				 * analysis of data coverage. Se edita el nombre de client1
				 * siendo client1.
				 */
				{ name, clientId, "client1", null },
				/*
				 * a) Functional requirements 11.2 - An actor who is
				 * authenticated must be able to: Edit his or her personal data.
				 * EDIT b)NEGATIVE TEST - Business rule: actors can only edit
				 * his own data. c) analysis of sentence coverage: 91.8% d) analysis
				 * of data coverage. Se intenta editar el nombre de client1
				 * siendo client2.
				 */
				{ name, clientId, "client2",
						java.lang.IllegalArgumentException.class },

				/*
				 * a) Functional requirements 11.2 - An actor who is
				 * authenticated must be able to: Edit his or her personal data.
				 * EDIT b)NEGATIVE TEST - Business rule: Actor must have a name.
				 * c) analysis of sentence coverage: 91.8% d) analysis of data
				 * coverage. Se intenta editar el nombre de client1 a null
				 * siendo client1.
				 */
				{ null, clientId, "client1",
						javax.validation.ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEditPersonalData((String) testingData[i][0],
						(int) testingData[i][1], (String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	protected void templateEditPersonalData(final String name,
			final int clientId, final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();

			Client client = clientService.findOne(clientId);
			client.setName(name);
			clientService.save(client);

			clientService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
