
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ParticipationEvent;

@Repository
public interface ParticipationEventRepository extends JpaRepository<ParticipationEvent, Integer> {

	@Query("select p from ParticipationEvent p where p.client.id =?1")
	Collection<ParticipationEvent> findByClientId(int clientId);
}
