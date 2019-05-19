
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ClubRepository;
import domain.Club;

@Service
@Transactional
public class ClubService {

	// Repository-----------------------------------------------

	@Autowired
	private ClubRepository			clubRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ClubService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Club create(final String authority) {
		final Club club = new Club();
		
		return club;
	}

	public List<Club> findAll() {
		return this.clubRepository.findAll();
	}

	public Club findOne(final Integer clubId) {
		return this.clubRepository.findOne(clubId);
	}

	public Club save(final Club club) {
		Assert.notNull(club);
		final Club saved = this.clubRepository.save(club);
		return saved;
	}

	public void delete(final Club club) {
		this.clubRepository.delete(club);
	}

	// Other Methods--------------------------------------------
}
