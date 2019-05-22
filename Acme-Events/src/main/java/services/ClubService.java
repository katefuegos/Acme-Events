
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ClubRepository;
import security.LoginService;
import domain.Client;
import domain.Club;
import domain.Follow;

@Service
@Transactional
public class ClubService {

	// Repository-----------------------------------------------

	@Autowired
	private ClubRepository	clubRepository;

	// Services-------------------------------------------------
	@Autowired
	private FollowService	followService;

	@Autowired
	private ClientService	clientService;


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

	public Club findByFollow(final int followId) {
		return this.clubRepository.findByFollow(followId);
	}

	public Collection<Club> findByClient(final Client client) {

		return this.clubRepository.findByClientId(client.getId());
	}

	public Collection<Club> findByUnFollow(final Client client) {

		return this.clubRepository.findByUnFollow(client.getId());
	}

	public Club findClubByClient(final int clientId, final int clubId) {
		return this.clubRepository.findClubByClient(clientId, clubId);
	}

	public void followClub(final Club club) {

		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
		Assert.notNull(client);
		Assert.isTrue(this.findClubByClient(client.getId(), club.getId()) == null, "club.error.follow.exist");

		final Follow follow = this.followService.create();
		final Follow saved = this.followService.save(follow);
		club.getFollows().add(saved);

		this.save(club);

	}

	public void unFollowClub(final int followId) {
		final Follow follow = this.followService.findOne(followId);
		Assert.notNull(follow, "follow.error.unexist");

		final Club club = this.findByFollow(followId);
		Assert.notNull(club, "club.error.unexist");

		club.getFollows().remove(follow);

		this.followService.delete(follow);

		this.save(club);

	}
}
