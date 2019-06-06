
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PubliciterRepository;
import security.Authority;
import security.UserAccount;
import domain.Publiciter;

@Service
@Transactional
public class PubliciterService {

	// Repository-----------------------------------------------

	@Autowired
	private PubliciterRepository	publiciterRepository;


	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public PubliciterService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Publiciter create() {
		final Publiciter publiciter = new Publiciter();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();

		final Authority a = new Authority();
		a.setAuthority(Authority.PUBLICITER);
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		userAccount.setEnabled(true);
		publiciter.setUserAccount(userAccount);

		publiciter.setIsBanned(false);
		publiciter.setIsSuspicious(false);

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
	
	public Publiciter findPubliciterByUserAccountId(int userAccountId){
		Assert.notNull(userAccountId);
		return publiciterRepository.findPubliciterByUserAccountId(userAccountId);
	}
}
