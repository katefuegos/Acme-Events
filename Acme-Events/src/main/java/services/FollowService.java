
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FollowRepository;
import security.LoginService;
import domain.Client;
import domain.Follow;

@Service
@Transactional
public class FollowService {

	// Repository-----------------------------------------------

	@Autowired
	private FollowRepository	followRepository;

	// Services-------------------------------------------------
	@Autowired
	private ClientService		clientService;


	// Constructor----------------------------------------------

	public FollowService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Follow create() {
		final Follow follow = new Follow();

		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());

		follow.setClient(client);
		follow.setMoment(new Date(System.currentTimeMillis() - 1000));

		return follow;
	}

	public List<Follow> findAll() {
		return this.followRepository.findAll();
	}

	public Follow findOne(final Integer followId) {
		return this.followRepository.findOne(followId);
	}

	public Follow save(final Follow follow) {
		Assert.notNull(follow);
		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
		Assert.isTrue(follow.getClient().equals(client), "follow.error.client");

		final Follow saved = this.followRepository.save(follow);
		return saved;
	}

	public void delete(final Follow follow) {
		this.followRepository.delete(follow);
	}

	// Other Methods--------------------------------------------

	public Follow findFollowByClient(final int clientId, final int clubId) {
		Follow result;

		result = this.followRepository.findFollowByClient(clientId, clubId);

		return result;
	}
}
