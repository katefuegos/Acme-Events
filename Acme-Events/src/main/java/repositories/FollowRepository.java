
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

	@Query("select f from Club c join c.follows f where f.client.id = ?1 and c.id= ?2")
	Follow findFollowByClient(int clientId, int clubId);

}
