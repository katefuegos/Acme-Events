
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PubliciterRepository;
import domain.Publiciter;

@Service
@Transactional
public class PubliciterService {

	// Repository-----------------------------------------------

	@Autowired
	private PubliciterRepository			publiciterRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public PubliciterService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Publiciter create(final String authority) {
		final Publiciter publiciter = new Publiciter();
		
		return publiciter;
	}

	public List<Publiciter> findAll() {
		return this.publiciterRepository.findAll();
	}

	public Publiciter findOne(final Integer publiciterId) {
		return this.publiciterRepository.findOne(publiciterId);
	}

	public Publiciter save(final Publiciter publiciter) {
		Assert.notNull(publiciter);
		final Publiciter saved = this.publiciterRepository.save(publiciter);
		return saved;
	}

	public void delete(final Publiciter publiciter) {
		this.publiciterRepository.delete(publiciter);
	}

	// Other Methods--------------------------------------------
}
