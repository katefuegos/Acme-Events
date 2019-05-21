
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.LoginService;
import domain.Actor;
import domain.Category;
import domain.Event;

@Service
@Transactional
public class CategoryService {

	// Repository-----------------------------------------------
	@Autowired
	private CategoryRepository	categoryRepository;

	// Services-------------------------------------------------
	@Autowired
	private EventService		eventService;

	@Autowired
	private ActorService		actorService;


	// Constructor----------------------------------------------
	public CategoryService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Category create() {
		final Category category = new Category();
		category.setRoot(false);

		return category;
	}

	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final Integer categoryId) {
		return this.categoryRepository.findOne(categoryId);
	}

	public Category save(final Category category) {
		Assert.notNull(category, "CATEGORY A CREAR/EDITAR NO PUEDE SER NULL");

		// ASIGNAR A ROOT SI NO TIENE PADRE
		if (category.getRootCategory() == null)
			category.setRootCategory(this.categoryRepository.findRootCategory());

		// COJO ACTOR ACTUAL
		final Actor actorActual = this.actorService.findActorByUsername(LoginService.getPrincipal().getUsername());
		Assert.notNull(actorActual, "NO HAY ACTOR DETECTADO");

		// COMPRUEBO RESTRICCIONES DE USUARIOS
		if (!actorActual.getUserAccount().getAuthorities().toString().contains("ADMIN"))
			Assert.notNull(null, "SOLO PUEDE CREAR/EDITAR CATEGORY ADMIN");

		// GUARDO CATEGORY
		final Category saved = this.categoryRepository.save(category);

		return saved;
	}

	public void delete(final Category category) {
		Assert.notNull(category, "CATEGORY A BORRAR NO PUEDE SER NULL");
		Assert.isTrue(category.getRootCategory() != null, "NO SE PUEDE BORRAR CATEGORY ROOT");

		// COJO ACTOR ACTUAL
		final Actor actorActual = this.actorService.findActorByUsername(LoginService.getPrincipal().getUsername());
		Assert.notNull(actorActual, "NO HAY ACTOR DETECTADO");

		// COMPRUEBO RESTRICCIONES DE USUARIOS
		if (!actorActual.getUserAccount().getAuthorities().toString().contains("ADMIN"))
			Assert.notNull(null, "SOLO PUEDE BORRAR CATEGORY ADMIN");

		// BORRO CATEGORIES HIJAS
		final Collection<Category> hijos = this.findSons(category.getId());
		if (!hijos.isEmpty())
			for (final Category c : hijos)
				this.delete(c);

		// ASIGNAR CATEGORY PADRE A FIXUPTASK
		final Collection<Event> events = this.eventService.findEventsByCategoryId(category.getId());
		if (!events.isEmpty())
			for (final Event f : events) {
				f.setCategory(category.getRootCategory());
				this.eventService.save(f);
			}

		// BORRO CATEGORY
		this.categoryRepository.delete(category);

	}

	// Other Methods--------------------------------------------

	public Category findRootCategory() {
		return this.categoryRepository.findRootCategory();
	}

	public Collection<Category> findSons(final Integer categoryId) {
		return this.categoryRepository.findSons(categoryId);
	}
}
