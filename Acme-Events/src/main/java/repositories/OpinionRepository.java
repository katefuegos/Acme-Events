
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

	@Query("select o from Event e join e.opinions o where e.id = ?1")
	Collection<Opinion> findByEvent(int e);

	@Query("select (sum(o.score)*1.0)/(1.0*count(o)) from Event e join e.opinions o where e.id = ?1")
	Double calculateScoreEvent(int eventId);

	@Query("select (sum(e.score)*1.0)/(1.0*count(e)) from Event e where e.club.id = ?1")
	Double calculateScoreClub(int clubId);

}
