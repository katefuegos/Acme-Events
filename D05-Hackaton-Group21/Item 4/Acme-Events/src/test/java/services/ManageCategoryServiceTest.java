package services;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Category;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManageCategoryServiceTest extends AbstractTest {

	// System Under Test ------------------------------------------------------

	@Autowired
	private CategoryService categoryService;

	// Tests ------------------------------------------------------------------

	// -------------------------Create--------------------------
	@SuppressWarnings("unchecked")
	@Test
	public void driverCreate() {
		final Map<String, String> map = new HashMap<String, String>();
		final String name = "name";
		final String nombre = "nombre";
		map.put("EN", name);
		map.put("ES", nombre);
		final Map<String, String> mapEmpty = new HashMap<String, String>();

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 15.2 An actor who is
				 * authenticated as an administrator must be able to: Manage the
				 * catalogue of categories, which includes listing, showing,
				 * creating, updating, and deleting them. Note that categories
				 * evolve independently from events, which means that they can
				 * be created, modified, or deleted independently from whether
				 * they are referenced from an event or not.
				 * 
				 * - CREATE. b) POSITIVE TEST c) analysis of sentence coverage:
				 * 94.6% d) analysis of data coverage - Se crea una categoría siendo
				 * admin.
				 */
				{

				map, "admin", null },
				/*
				 * a) Functional requirements - 15.2 An actor who is
				 * authenticated as an administrator must be able to: Manage the
				 * catalogue of categories, which includes listing, showing,
				 * creating, updating, and deleting them. Note that categories
				 * evolve independently from events, which means that they can
				 * be created, modified, or deleted independently from whether
				 * they are referenced from an event or not.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: Only
				 * administrators can create categories. c) analysis of sentence
				 * coverage: 94.6% d) analysis of data coverage - Se intenta crear
				 * una categoría siendo manager1.
				 */
				{

				map, "manager1", java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 15.2 An actor who is
				 * authenticated as an administrator must be able to: Manage the
				 * catalogue of categories, which includes listing, showing,
				 * creating, updating, and deleting them. Note that categories
				 * evolve independently from events, which means that they can
				 * be created, modified, or deleted independently from whether
				 * they are referenced from an event or not.
				 * 
				 * - CREATE. b) NEGATIVE TEST - Business rule: Categories must
				 * have a name. c) analysis of sentence coverage: 94.6% d) analysis
				 * of data coverage - Se intenta crear una categoría sin nombre
				 * siendo admin.
				 */
				{

				mapEmpty, "admin",
						javax.validation.ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateCreate((Map<String, String>) testingData[i][0],
						(String) testingData[i][1],
						(Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreate(final Map<String, String> map,
			final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Category category = this.categoryService.create();

			category.setTitle(map);

			this.categoryService.save(category);

			this.categoryService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Edit--------------------------
	@SuppressWarnings("unchecked")
	@Test
	public void driverEdit() {
		final Map<String, String> map = new HashMap<String, String>();
		final String name = "name";
		final String nombre = "nombre";
		map.put("EN", name);
		map.put("ES", nombre);
		final Map<String, String> mapEmpty = new HashMap<String, String>();
		final int categoryId = super.getEntityId("category1");

		final Object testingData[][] = {
				/*
				 * a) Functional requirements - 15.2 An actor who is
				 * authenticated as an administrator must be able to: Manage the
				 * catalogue of categories, which includes listing, showing,
				 * creating, updating, and deleting them. Note that categories
				 * evolve independently from events, which means that they can
				 * be created, modified, or deleted independently from whether
				 * they are referenced from an event or not.
				 * 
				 * - EDIT. b) POSITIVE TEST c) analysis of sentence coverage: 94.9%
				 * d) analysis of data coverage - Se edita una categoría siendo
				 * admin.
				 */
				{

				categoryId, map, "admin", null },
				/*
				 * a) Functional requirements - 15.2 An actor who is
				 * authenticated as an administrator must be able to: Manage the
				 * catalogue of categories, which includes listing, showing,
				 * creating, updating, and deleting them. Note that categories
				 * evolve independently from events, which means that they can
				 * be created, modified, or deleted independently from whether
				 * they are referenced from an event or not.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: Only administrators
				 * can edit categories. c) analysis of sentence coverage: 94.9% d)
				 * analysis of data coverage - Se intenta editar una categoría
				 * siendo manager1.
				 */
				{

				categoryId, map, "manager1",
						java.lang.IllegalArgumentException.class },
				/*
				 * a) Functional requirements - 15.2 An actor who is
				 * authenticated as an administrator must be able to: Manage the
				 * catalogue of categories, which includes listing, showing,
				 * creating, updating, and deleting them. Note that categories
				 * evolve independently from events, which means that they can
				 * be created, modified, or deleted independently from whether
				 * they are referenced from an event or not.
				 * 
				 * - EDIT. b) NEGATIVE TEST - Business rule: Categories must
				 * have a name. c) analysis of sentence coverage: 94.9% d) analysis
				 * of data coverage - Se intenta editar una categoría sin nombre
				 * siendo admin.
				 */
				{

				categoryId, mapEmpty, "admin",
						javax.validation.ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.templateEdit((int) testingData[i][0],
						(Map<String, String>) testingData[i][1],
						(String) testingData[i][2],
						(Class<?>) testingData[i][3]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateEdit(final int categoryId,
			final Map<String, String> map, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Category category = this.categoryService.findOne(categoryId);

			category.setTitle(map);

			this.categoryService.save(category);

			this.categoryService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// -------------------------Delete--------------------------
	@Test
	public void driverDelete() {
		final int categoryId = super.getEntityId("category1");

		final Object testingData[][] = {
		/*
		 * a) Functional requirements - 15.2 An actor who is authenticated as an
		 * administrator must be able to: Manage the catalogue of categories,
		 * which includes listing, showing, creating, updating, and deleting
		 * them. Note that categories evolve independently from events, which
		 * means that they can be created, modified, or deleted independently
		 * from whether they are referenced from an event or not.
		 * 
		 * - DELETE. b) POSITIVE TEST c) analysis of sentence coverage: 94.3% d)
		 * analysis of data coverage - Se elimina una categoría siendo admin.
		 */
		{

		categoryId, "admin", null },
		/*
		 * a) Functional requirements - 15.2 An actor who is authenticated as an
		 * administrator must be able to: Manage the catalogue of categories,
		 * which includes listing, showing, creating, updating, and deleting
		 * them. Note that categories evolve independently from events, which
		 * means that they can be created, modified, or deleted independently
		 * from whether they are referenced from an event or not.
		 * 
		 * - DELETE. b) NEGATIVE TEST - Business rule: Only administrators can
		 * delete categories. c) analysis of sentence coverage: 94.3% d) analysis of
		 * data coverage - Se intenta eliminar una categoría siendo manager1.
		 */
		{

		categoryId, "manager1", java.lang.IllegalArgumentException.class },
		/*
		 * a) Functional requirements - 15.2 An actor who is authenticated as an
		 * administrator must be able to: Manage the catalogue of categories,
		 * which includes listing, showing, creating, updating, and deleting
		 * them. Note that categories evolve independently from events, which
		 * means that they can be created, modified, or deleted independently
		 * from whether they are referenced from an event or not.
		 * 
		 * - DELETE. b) NEGATIVE TEST - Business rule: Actor can't delete a
		 * category who don't exists. c) analysis of sentence coverage: 94.3% d)
		 * analysis of data coverage - Se intenta eliminar una categoría
		 * inexistente siendo admin.
		 */
		{

		12345, "admin", java.lang.IllegalArgumentException.class },

		};

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

	protected void templateDelete(final int categoryId, final String username,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (username != null)
				super.authenticate(username);
			else
				super.unauthenticate();
			final Category category = this.categoryService.findOne(categoryId);

			this.categoryService.delete(category);

			this.categoryService.flush();

			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
