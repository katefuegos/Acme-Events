
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query("select c from Client c where c.userAccount.id = ?1")
	Client findClientByUserAccount(int userAccountId);

	@Query("select c from Client c where c.userAccount.username=?1")
	Client findClientByUsername(String username);

}
