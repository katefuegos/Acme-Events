
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	// Repository-----------------------------------------------

	@Autowired
	private CategoryRepository			categoryRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public CategoryService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Category create(final String authority) {
		final Category category = new Category();
		
		return category;
	}

	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final Integer categoryId) {
		return this.categoryRepository.findOne(categoryId);
	}

	public Category save(final Category category) {
		Assert.notNull(category);
		final Category saved = this.categoryRepository.save(category);
		return saved;
	}

	public void delete(final Category category) {
		this.categoryRepository.delete(category);
	}

	// Other Methods--------------------------------------------
}
