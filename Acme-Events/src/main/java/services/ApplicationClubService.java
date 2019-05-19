
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationClubRepository;
import domain.ApplicationClub;

@Service
@Transactional
public class ApplicationClubService {

	// Repository-----------------------------------------------

	@Autowired
	private ApplicationClubRepository			applicationClubRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ApplicationClubService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public ApplicationClub create(final String authority) {
		final ApplicationClub applicationClub = new ApplicationClub();
		
		return applicationClub;
	}

	public List<ApplicationClub> findAll() {
		return this.applicationClubRepository.findAll();
	}

	public ApplicationClub findOne(final Integer applicationClubId) {
		return this.applicationClubRepository.findOne(applicationClubId);
	}

	public ApplicationClub save(final ApplicationClub applicationClub) {
		Assert.notNull(applicationClub);
		final ApplicationClub saved = this.applicationClubRepository.save(applicationClub);
		return saved;
	}

	public void delete(final ApplicationClub applicationClub) {
		this.applicationClubRepository.delete(applicationClub);
	}

	// Other Methods--------------------------------------------
}
