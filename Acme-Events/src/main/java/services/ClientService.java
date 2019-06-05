
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ClientRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Client;

@Service
@Transactional
public class ClientService {

	// Repository-----------------------------------------------

	@Autowired
	private ClientRepository	clientRepository;


	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ClientService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Client create() {
		final Client client = new Client();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();

		final Authority a = new Authority();
		a.setAuthority(Authority.CLIENT);
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		userAccount.setEnabled(true);
		client.setUserAccount(userAccount);

		client.setIsBanned(false);
		client.setIsSuspicious(false);

		return client;
	}

	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

	public Client findOne(final Integer clientId) {
		return this.clientRepository.findOne(clientId);
	}

	public Client save(final Client client) {
		Assert.notNull(client);
		if(client.getId()!=0){
			final UserAccount ua = LoginService.getPrincipal();
			Assert.notNull(ua);
			Assert.isTrue(client.getUserAccount().equals(ua));
		}
		
		final Client saved = this.clientRepository.save(client);
		return saved;
	}

	public void delete(final Client client) {
		this.clientRepository.delete(client);
	}
	
	public void flush() {
		this.clientRepository.flush();

	}

	// Other Methods--------------------------------------------

	public Client isSuspicious(final Client client) {
		final Client saved = this.clientRepository.save(client);

		return saved;
	}

	public Client findClientByUseraccount(final UserAccount userAccount) {
		return this.clientRepository.findClientByUserAccount(userAccount.getId());

	}

	public Client findClientByUsername(final String username) {
		return this.clientRepository.findClientByUsername(username);
	}

}
