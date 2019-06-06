
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationJobRepository;
import domain.ApplicationJob;

@Service
@Transactional
public class ApplicationJobService {

	// Repository-----------------------------------------------

	@Autowired
	private ApplicationJobRepository			applicationJobRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ApplicationJobService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public ApplicationJob create(final String authority) {
		final ApplicationJob applicationJob = new ApplicationJob();
		
		return applicationJob;
	}

	public List<ApplicationJob> findAll() {
		return this.applicationJobRepository.findAll();
	}

	public ApplicationJob findOne(final Integer applicationJobId) {
		return this.applicationJobRepository.findOne(applicationJobId);
	}

	public ApplicationJob save(final ApplicationJob applicationJob) {
		Assert.notNull(applicationJob);
		final ApplicationJob saved = this.applicationJobRepository.save(applicationJob);
		return saved;
	}

	public void delete(final ApplicationJob applicationJob) {
		this.applicationJobRepository.delete(applicationJob);
	}

	// Other Methods--------------------------------------------
}
