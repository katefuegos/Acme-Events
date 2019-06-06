
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Publiciter;

@Repository
public interface PubliciterRepository extends JpaRepository<Publiciter, Integer> {

	@Query("select p from Publiciter p where p.userAccount.id = ?1")
	Publiciter findPubliciterByUserAccountId(int userAccountId);

}
