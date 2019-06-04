
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ClubRepository;
import security.LoginService;
import security.UserAccount;
import domain.ApplicationClub;
import domain.Client;
import domain.Club;
import domain.Follow;
import domain.Manager;
import forms.ClubForm;

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

	@Autowired
	private ManagerService	managerService;


	// Constructor----------------------------------------------

	public ClubService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Club create() {
		final Club club = new Club();
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString().contains("MANAGER"));
		final Collection<Follow> follows = new ArrayList<Follow>();
		final Collection<ApplicationClub> appclubs = new ArrayList<ApplicationClub>();
		club.setManager(this.managerService.findManagerByUserAccount(LoginService.getPrincipal().getId()));
		club.setFollows(follows);
		club.setApplicationsClub(appclubs);
		club.setDraftMode(true);
		club.setAccepted(false);
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
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString().contains("MANAGER"));
		final Manager manager = this.managerService.findManagerByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(manager);
		Assert.isTrue(club.getManager().equals(manager));
		final Club saved = this.clubRepository.save(club);
		return saved;
	}

	public void delete(final Club club) {
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString().contains("MANAGER"));
		final Manager manager = this.managerService.findManagerByUserAccount(LoginService.getPrincipal().getId());
		Assert.isTrue(club.isDraftMode());
		Assert.isTrue(club.getManager().equals(manager));
		this.clubRepository.delete(club);
	}

	// Other Methods--------------------------------------------

	public void updateScore(final Club club, final Double score) {
		club.setScore(score);
		this.clubRepository.save(club);
	}

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
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString().contains("CLIENT"));
		Assert.isTrue(club.isAccepted());
		Assert.isTrue(this.findClubByClient(client.getId(), club.getId()) == null, "club.error.follow.exist");

		final Follow follow = this.followService.create();
		final Follow saved = this.followService.save(follow);
		club.getFollows().add(saved);

		this.clubRepository.save(club);

	}

	public void unFollowClub(final Club club) {
		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
		Assert.notNull(client);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString().contains("CLIENT"));
		Assert.notNull(club, "club.error.unexist");
		Assert.isTrue(this.findClubByClient(client.getId(), club.getId()) != null, "club.error.follow.unexist");

		// Seleccionamos el seguimiento
		final Follow follow = this.followService.findFollowByClient(client.getId(), club.getId());
		Assert.notNull(follow, "follow.error.unexist");

		//Eliminamos la relación con club
		club.getFollows().remove(follow);
		this.clubRepository.save(club);

		//Borramos el seguimiento
		this.followService.delete(follow);

	}

	public void accept(final Club club) {

		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		Assert.isTrue(ua.getAuthorities().toString().contains("ADMIN"));
		Assert.notNull(club);
		Assert.isTrue(!club.isAccepted());
		Assert.isTrue(club.getReasonReject() == null);

		club.setAccepted(true);

		this.clubRepository.save(club);
	}

	public void reject(final ClubForm clubForm) {

		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		Assert.isTrue(ua.getAuthorities().toString().contains("ADMIN"));
		final Club club = this.findOne(clubForm.getId());
		Assert.notNull(club);
		Assert.isTrue(!club.isAccepted());
		Assert.isTrue(club.getReasonReject() == null);

		club.setAccepted(false);
		club.setReasonReject(clubForm.getReasonReject());

		this.clubRepository.save(club);
	}

	public Collection<Club> findClubsPending() {
		final Collection<Club> result = this.clubRepository.findClubsPending();
		return result;
	}

	public Collection<Club> findClubsAccepted() {
		final Collection<Club> result = this.clubRepository.findClubsAccepted();
		return result;
	}

	public Collection<Club> findClubsAccepted(final int eventId) {
		final Collection<Club> result = this.clubRepository.findClubsAccepted(eventId);
		return result;
	}

	public Collection<Club> findClubsRejected() {
		final Collection<Club> result = this.clubRepository.findClubsRejected();
		return result;
	}

	public Collection<Club> findByManagerId(final int managerId) {
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString().contains("MANAGER"));
		final Collection<Club> result = this.clubRepository.findByManagerId(managerId);
		return result;
	}

	public Collection<Club> findByManagerIdAndAcepted(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerIdAndAccepted(managerId);
		return result;
	}

	public Collection<Club> findByManagerAndAcepted(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerAndAccepted(managerId);
		return result;
	}

	public Collection<Club> findByManagerIdAndPending(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerIdAndPending(managerId);
		return result;
	}

	public Collection<Club> findByManagerIdAndRejected(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerIdAndRejected(managerId);
		return result;
	}

	public Collection<Club> findByManagerIdAndDraftMode(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerIdAndDraftMode(managerId);
		return result;
	}

	public Collection<Club> findClubsDraftMode() {
		final Collection<Club> result = this.clubRepository.findClubsDraftMode();
		return result;
	}

	public void flush() {
		this.clubRepository.flush();

	}
	public Collection<Club> findByManagerAndAccepted(final int managerId) {
		final Collection<Club> result = this.clubRepository.findByManagerAndAccepted(managerId);
		return result;

	}
}
