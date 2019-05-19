
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FollowRepository;
import domain.Follow;

@Service
@Transactional
public class FollowService {

	// Repository-----------------------------------------------

	@Autowired
	private FollowRepository			followRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public FollowService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Follow create(final String authority) {
		final Follow follow = new Follow();
		
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
		final Follow saved = this.followRepository.save(follow);
		return saved;
	}

	public void delete(final Follow follow) {
		this.followRepository.delete(follow);
	}

	// Other Methods--------------------------------------------
}
