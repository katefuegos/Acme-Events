
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialProfile;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Integer> {

	@Query("select p from SocialProfile p where p.actor.id=?1")
	Collection<SocialProfile> findProfilesByActor(int actorId);

	@Query("select p from SocialProfile p where p.actor.userAccount.id = ?1")
	Collection<SocialProfile> findProfileByUserAccount(int userAccountId);

}
