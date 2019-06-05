package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Configuration;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EditConfigurationServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private ConfigurationService configurationService;

	// Tests ------------------------------------------------------------------

	// -------------------------Edit--------------------------
	@Test
	public void driverEditConfiguration() {
		final String nameSystem = "nameSystem";

		final Object testingData[][] = {
		/*
		 * a) Functional requirements - 17. The system must be easy to customise
		 * at run time.
		 * 
		 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage: 94.6% d)
		 * analysis of data coverage - Se edita el nombre del sistema siendo
		 * admin.
		 */
		{

		nameSystem, "admin", null },
		/*
		 * a) Functional requirements - 17. The system must be easy to customise
		 * at run time.
		 * 
		 * - EDIT. b) NEGATIVE TEST - Business rule: Only administrators can
		 * edit the configuration of the system. c) analysis of sentence
		 * coverage: 94.6% d) analysis of data coverage - Se intenta editar el
		 * nombre del sistema siendo manager1.
		 */
		{

		nameSystem, "manager1", java.lang.IllegalArgumentException.class },
		/*
		 * a) Functional requirements - 17. The system must be easy to customise
		 * at run time.
		 * 
		 * - CREATE. b) NEGATIVE TEST - Business rule: The system must have a
		 * system name. c) analysis of sentence coverage: 94.6% d) analysis of data
		 * coverage - Se intenta editar el nombre del sistema sin introducir
		 * ningún dato siendo admin.
		 */
		{

		"", "admin", javax.validation.ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEditConfiguration((String) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEditConfiguration(final String nameSystem,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Configuration configuration = this.configurationService
					.findOne();

			configuration.setSystemName(nameSystem);

			this.configurationService.save(configuration);

			this.configurationService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
