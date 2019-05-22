
package repositories;

import java.util.Collection;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Query("select p from Profile p where p.manager.id=?1")
	Collection<Profile> findProfilesByManager(int managerId);

}
