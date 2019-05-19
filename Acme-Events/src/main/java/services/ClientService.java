
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ClientRepository;
import domain.Client;

@Service
@Transactional
public class ClientService {

	// Repository-----------------------------------------------

	@Autowired
	private ClientRepository			clientRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ClientService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Client create(final String authority) {
		final Client client = new Client();
		
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
		final Client saved = this.clientRepository.save(client);
		return saved;
	}

	public void delete(final Client client) {
		this.clientRepository.delete(client);
	}

	// Other Methods--------------------------------------------
}
