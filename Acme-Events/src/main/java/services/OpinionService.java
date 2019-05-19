
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OpinionRepository;
import domain.Opinion;

@Service
@Transactional
public class OpinionService {

	// Repository-----------------------------------------------

	@Autowired
	private OpinionRepository			opinionRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public OpinionService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Opinion create(final String authority) {
		final Opinion opinion = new Opinion();
		
		return opinion;
	}

	public List<Opinion> findAll() {
		return this.opinionRepository.findAll();
	}

	public Opinion findOne(final Integer opinionId) {
		return this.opinionRepository.findOne(opinionId);
	}

	public Opinion save(final Opinion opinion) {
		Assert.notNull(opinion);
		final Opinion saved = this.opinionRepository.save(opinion);
		return saved;
	}

	public void delete(final Opinion opinion) {
		this.opinionRepository.delete(opinion);
	}

	// Other Methods--------------------------------------------
}
