
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
		final Manager saved = this.managerRepository.save(manager);
		return saved;
	}

	public void delete(final Manager manager) {
		this.managerRepository.delete(manager);
	}

	// Other Methods--------------------------------------------
}
