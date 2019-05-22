
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {

<<<<<<< HEAD
=======
	@Query("select c from Club c join c.follows f where f.id =?1")
	Club findByFollow(int followId);

	@Query("select c from Club c join c.follows f where f.client.id =?1")
	Collection<Club> findByClientId(int clientId);

	@Query("select c from Club c join c.follows f where f.client.id = ?1 and c.id= ?2")
	Club findClubByClient(int clientId, int clubId);

	@Query("select DISTINCT(c) from Club c join c.follows f where f.client.id <> ?1 ")
	Collection<Club> findByUnFollow(int clientId);

	//	@Query("select c.name, f.moment, f.id from Club c join c.follows f where f.client.id = ?1")
	//	Collection<ClubFollowForm> findClubFollows(int clientId);

>>>>>>> f0168c0b05c95735f8f8b2fa3dc1eb30b38717dc
	@Query("select c from Club c where c.accepted = false and c.reasonReject is null")
	Collection<Club> findClubsPending();

	@Query("select c from Club c where c.accepted = true")
	Collection<Club> findClubsAccepted();

	@Query("select c from Club c where c.accepted = false and c.reasonReject is not null")
	Collection<Club> findClubsRejected();

	@Query("select c from Club c where c.manager.id=?1")
	Collection<Club> findByManagerId(int managerId);
}
