
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import forms.ActorForm;

@Service
@Transactional
public class ActorService {

	// Repository-----------------------------------------------

	@Autowired
	private ActorRepository			actorRepository;

	// Services-------------------------------------------------

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private ClientService			clientService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ServiceUtils			serviceUtils;

	@Autowired
	private BoxService				boxService;


	// Constructor----------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Actor create() {
		final Actor actor = new Actor();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();

		final Authority a = new Authority();
		a.setAuthority("CLIENT");
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		userAccount.setEnabled(true);
		actor.setUserAccount(userAccount);
		actor.setIsBanned(false);
		actor.setIsSuspicious(false);
		return actor;
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findOne(final Integer actorId) {
		return this.actorRepository.findOne(actorId);
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		final Actor saved = this.actorRepository.save(actor);
		return saved;
	}

	public void delete(final Actor actor) {
		this.actorRepository.delete(actor);
	}

	// Other Methods--------------------------------------------

	public Actor findActorByUsername(final String username) {
		final Actor actor = this.actorRepository.findActorByUsername(username);
		return actor;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		return this.actorRepository.findByUserAccountId(userAccount.getId());
	}
	public Actor findPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		return this.actorRepository.findByUserAccountId(userAccount.getId());
	}
	public Actor findByUserAccountId(final int id) {
		return this.actorRepository.findByUserAccountId(id);
	}

	public void ban(final Actor actor) {
		actor.setIsBanned(true);
		actor.getUserAccount().setEnabled(false);
		this.save(actor);
	}

	public void unban(final Actor actor) {
		actor.setIsBanned(false);
		actor.setIsSuspicious(false);
		actor.getUserAccount().setEnabled(true);
		this.save(actor);

	}

	public void update(final ActorForm actorform) {

		Assert.notNull(actorform);

		final Collection<Authority> authorities = actorform.getUserAccount().getAuthorities();
		final Authority manager = new Authority();
		manager.setAuthority(Authority.MANAGER);
		final Authority client = new Authority();
		client.setAuthority(Authority.CLIENT);

		final Authority admin = new Authority();
		admin.setAuthority(Authority.ADMIN);

		if (authorities.contains(manager)) {
			domain.Manager mana = null;
			if (actorform.getId() != 0)
				mana = this.managerService.findOne(actorform.getId());
			else {
				mana = this.managerService.create();
				mana.setUserAccount(actorform.getUserAccount());
				// Assert.isTrue(LoginService.getPrincipal() == null);
				Assert.isTrue(this.serviceUtils.checkAuthorityBoolean(null));
			}
			mana.setId(actorform.getId());
			mana.setVersion(actorform.getVersion());
			mana.setName(actorform.getName());
			mana.setMiddleName(actorform.getMiddleName());
			mana.setSurname(actorform.getSurname());
			mana.setPhoto(actorform.getPhoto());
			mana.setEmail(actorform.getEmail());
			mana.setPhone(actorform.getPhone());
			mana.setAddress(actorform.getAddress());

			final Actor a = this.managerService.save(mana);

			this.boxService.addSystemBox(a);

		} else if (authorities.contains(client)) {
			domain.Client cli = null;
			if (actorform.getId() != 0)
				cli = this.clientService.findOne(actorform.getId());
			else {
				cli = this.clientService.create();
				cli.setUserAccount(actorform.getUserAccount());
				// Assert.isTrue(LoginService.getPrincipal() == null);
				Assert.isTrue(this.serviceUtils.checkAuthorityBoolean(null));
			}

			cli.setId(actorform.getId());
			cli.setVersion(actorform.getVersion());
			cli.setName(actorform.getName());
			cli.setMiddleName(actorform.getMiddleName());
			cli.setSurname(actorform.getSurname());
			cli.setPhoto(actorform.getPhoto());
			cli.setEmail(actorform.getEmail());
			cli.setPhone(actorform.getPhone());
			cli.setAddress(actorform.getAddress());

			cli.setDNI(actorform.getDNI());

			final Actor a = this.clientService.save(cli);
			this.boxService.addSystemBox(a);

		} else if (authorities.contains(admin)) {
			Administrator administrator = null;

			Assert.isTrue(this.serviceUtils.checkAuthorityBoolean("ADMIN"));

			if (actorform.getId() != 0)
				administrator = this.administratorService.findOne(actorform.getId());
			else {
				administrator = this.administratorService.create();
				administrator.setUserAccount(actorform.getUserAccount());

			}

			administrator.setId(actorform.getId());
			administrator.setVersion(actorform.getVersion());
			administrator.setName(actorform.getName());
			administrator.setMiddleName(actorform.getMiddleName());
			administrator.setSurname(actorform.getSurname());
			administrator.setPhoto(actorform.getPhoto());
			administrator.setEmail(actorform.getEmail());
			administrator.setPhone(actorform.getPhone());
			administrator.setAddress(actorform.getAddress());

			final Actor a = this.administratorService.save(administrator);

			this.boxService.addSystemBox(a);

		}

	}

	public void flush() {
		this.actorRepository.flush();
	}
}
