
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	// Repository-----------------------------------------------

	@Autowired
	private ManagerRepository			managerRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Manager create(final String authority) {
		final Manager manager = new Manager();
		
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
