
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	// Repository-----------------------------------------------

	@Autowired
	private ManagerRepository	managerRepository;


	//	@Autowired
	//	private ConfigurationService	configurationService;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Manager create() {
		final Manager manager = new Manager();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();

		final Authority a = new Authority();
		a.setAuthority(Authority.MANAGER);
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		userAccount.setEnabled(true);
		manager.setUserAccount(userAccount);

		manager.setIsBanned(false);
		manager.setIsSuspicious(false);

		return manager;
	}

	public List<Manager> findAll() {
		return this.managerRepository.findAll();
	}

	public Manager findOne(final Integer managerId) {
		return this.managerRepository.findOne(managerId);
	}

	public Manager save(final Manager manager) {
		Assert.notNull(manager);
		if(manager.getId()!=0){
			final UserAccount ua = LoginService.getPrincipal();
			Assert.notNull(ua);
			Assert.isTrue(manager.getUserAccount().equals(ua));
		}
		final Manager saved = this.managerRepository.save(manager);
		return saved;
	}

	public void delete(final Manager manager) {
		this.managerRepository.delete(manager);
	}

	// Other Methods--------------------------------------------
	public Manager isSuspicious(final Manager manager) {
		final Manager saved = this.managerRepository.save(manager);

		return saved;
	}

	public Manager findManagerByUserAccount(final int userAccountId) {
		final Manager manager = this.managerRepository.findManagerByUserAccount(userAccountId);
		return manager;
	}

	public Manager findManagerByUsername(final String username) {
		final Manager manager = this.managerRepository.findManagerByUsername(username);
		return manager;
	}

}
