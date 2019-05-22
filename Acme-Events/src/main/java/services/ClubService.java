
package services;

import java.util.Collection;
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
	private ClubRepository	clubRepository;


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

	public Collection<Club> findClubsPending() {
		final Collection<Club> result = this.clubRepository.findClubsPending();
		return result;
	}

	public Collection<Club> findClubsAccepted() {
		final Collection<Club> result = this.clubRepository.findClubsAccepted();
		return result;
	}

	public Collection<Club> findClubsRejected() {
		final Collection<Club> result = this.clubRepository.findClubsRejected();
		return result;
	}

	public Collection<Club> findByManagerId(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerId(managerId);
		return result;
	}
}
