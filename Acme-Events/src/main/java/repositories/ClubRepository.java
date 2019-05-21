
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {


	@Query("select c from Club c where c.accepted = false and c.reasonReject is null")
	Collection<Club> findClubsPending();
	
	@Query("select c from Club c where c.accepted = true")
	Collection<Club> findClubsAccepted();
	
	@Query("select c from Club c where c.accepted = false and c.reasonReject is not null")
	Collection<Club> findClubsRejected();
}
