
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Integer> {

	@Query("select o from Opinion o where o.client.id = ?1")
	Collection<Opinion> findByClient(int clientId);

	@Query("select o from Event e join e.opinions o where e.club.manager.id = ?1")
	Collection<Opinion> findByManager(int managerId);
}
