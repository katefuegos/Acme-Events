
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OpinionRepository;
import security.LoginService;
import domain.Client;
import domain.Manager;
import domain.Opinion;

@Service
@Transactional
public class OpinionService {

	// Repository-----------------------------------------------

	@Autowired
	private OpinionRepository	opinionRepository;

	// Services-------------------------------------------------

	@Autowired
	private ClientService		clientService;


	// Constructor----------------------------------------------

	public OpinionService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Opinion create() {
		final Opinion opinion = new Opinion();
		final Client client = this.clientService.findClientByUseraccount(LoginService.getPrincipal());
		Assert.notNull(client);

		opinion.setClient(client);

		opinion.setMoment(new Date(System.currentTimeMillis() - 1000));

		return opinion;
	}

	public List<Opinion> findAll() {
		return this.opinionRepository.findAll();
	}

	public Opinion findOne(final Integer opinionId) {
		return this.opinionRepository.findOne(opinionId);
	}

	public Opinion save(final Opinion opinion) {
		Assert.notNull(opinion);

		final Opinion saved = this.opinionRepository.save(opinion);
		return saved;
	}
	public void delete(final Opinion opinion) {
		this.opinionRepository.delete(opinion);
	}

	// Other Methods--------------------------------------------
	public Collection<Opinion> findByClient(final Client client) {
		return this.opinionRepository.findByClient(client.getId());

	}

	public Collection<Opinion> findByManager(final Manager manager) {
		return this.opinionRepository.findByManager(manager.getId());

	}

}
