
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.Club;

@Service
@Transactional
public class AdministratorService {

	//Repository-----------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;

	//Services-------------------------------------------------

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private ClientService			clientService;


	//Constructor----------------------------------------------

	public AdministratorService() {

		super();
	}

	//Simple CRUD----------------------------------------------

	public Administrator create() {
		final Administrator administrator = new Administrator();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();

		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		userAccount.setEnabled(true);
		administrator.setUserAccount(userAccount);

		administrator.setIsBanned(false);
		administrator.setIsSuspicious(null);
		return administrator;
	}
	public List<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator findOne(final Integer administratorId) {
		return this.administratorRepository.findOne(administratorId);
	}

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);

		final Authority admin = new Authority();
		admin.setAuthority(Authority.ADMIN);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(admin), "actor.register.error.authority");
		if(administrator.getId()!=0){
			final UserAccount ua = LoginService.getPrincipal();
			Assert.notNull(ua);
			Assert.isTrue(administrator.getUserAccount().equals(ua));
		}

		final Administrator saved = this.administratorRepository.save(administrator);
		return saved;
	}
	public void delete(final Administrator entity) {
		this.administratorRepository.delete(entity);
	}

	//Other Methods--------------------------------------------

	public Actor isSuspicious(final Actor actor) {
		Actor result = null;
		final UserAccount userAccount = actor.getUserAccount();
		final Authority authority = userAccount.getAuthorities().iterator().next();

		switch (authority.getAuthority()) {
		case "ADMIN":
			final Administrator administrator = this.findByUseraccount(userAccount);
			administrator.setIsSuspicious(true);
			result = this.isSuspicious(administrator);
			break;
		case "CLIENT":
			final domain.Client client = this.clientService.findClientByUseraccount(userAccount);
			client.setIsSuspicious(true);
			result = this.clientService.isSuspicious(client);

			break;
		case "MANAGER":
			final domain.Manager manager = this.managerService.findManagerByUserAccount(userAccount.getId());
			manager.setIsSuspicious(true);
			result = this.managerService.isSuspicious(manager);
			break;
		}
		return result;
	}
	public Administrator isSuspicious(final Administrator administrator) {
		final Administrator saved = this.administratorRepository.save(administrator);

		return saved;
	}

	public Administrator findByUseraccount(final UserAccount userAccount) {
		return this.administratorRepository.findAdministratorByUserAccount(userAccount.getId());

	}

	public Administrator findAdminByUsername(final String username) {
		return this.administratorRepository.findAdminByUsername(username);
	}

	public Object[] queryC1() {
		Object[] result = null;

		result = this.administratorRepository.queryC1();

		return result;
	}

	public Object[] queryC2() {
		Object[] result = null;

		result = this.administratorRepository.queryC2();

		return result;
	}

	public Object[] queryC3() {
		Object[] result = null;

		result = this.administratorRepository.queryC3();

		return result;
	}

	public Object[] queryC4() {
		Object[] result = null;

		result = this.administratorRepository.queryC4();

		return result;
	}

	public Double queryC5() {
		Double result = null;

		result = this.administratorRepository.queryC5();

		return result;
	}

	public Double queryC6() {
		Double result = null;

		result = this.administratorRepository.queryC6();

		return result;
	}

	public Collection<Club> queryC7() {
		Collection<Club> result = null;

		result = this.administratorRepository.queryC7();

		return result;
	}

}
