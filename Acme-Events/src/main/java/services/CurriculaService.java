
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Curricula;

@Service
@Transactional
public class CurriculaService {

	// Repository-----------------------------------------------

	@Autowired
	private CurriculaRepository			curriculaRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public CurriculaService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Curricula create(final String authority) {
		final Curricula curricula = new Curricula();
		
		return curricula;
	}

	public List<Curricula> findAll() {
		return this.curriculaRepository.findAll();
	}

	public Curricula findOne(final Integer curriculaId) {
		return this.curriculaRepository.findOne(curriculaId);
	}

	public Curricula save(final Curricula curricula) {
		Assert.notNull(curricula);
		final Curricula saved = this.curriculaRepository.save(curricula);
		return saved;
	}

	public void delete(final Curricula curricula) {
		this.curriculaRepository.delete(curricula);
	}

	// Other Methods--------------------------------------------
}
