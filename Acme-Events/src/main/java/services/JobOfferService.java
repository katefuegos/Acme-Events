
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.JobOfferRepository;
import domain.JobOffer;

@Service
@Transactional
public class JobOfferService {

	// Repository-----------------------------------------------

	@Autowired
	private JobOfferRepository			jobOfferRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public JobOfferService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public JobOffer create(final String authority) {
		final JobOffer jobOffer = new JobOffer();
		
		return jobOffer;
	}

	public List<JobOffer> findAll() {
		return this.jobOfferRepository.findAll();
	}

	public JobOffer findOne(final Integer jobOfferId) {
		return this.jobOfferRepository.findOne(jobOfferId);
	}

	public JobOffer save(final JobOffer jobOffer) {
		Assert.notNull(jobOffer);
		final JobOffer saved = this.jobOfferRepository.save(jobOffer);
		return saved;
	}

	public void delete(final JobOffer jobOffer) {
		this.jobOfferRepository.delete(jobOffer);
	}

	// Other Methods--------------------------------------------
}
