
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select r from Manager r where r.userAccount.id = ?1")
	Manager findManagerByUserAccount(int userAccountId);

	@Query("select r from Manager r where r.userAccount.username=?1")
	Manager findManagerByUsername(String username);
	
}
