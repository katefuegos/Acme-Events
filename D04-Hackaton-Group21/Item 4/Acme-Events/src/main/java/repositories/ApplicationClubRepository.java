
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ApplicationClub;

@Repository
public interface ApplicationClubRepository extends JpaRepository<ApplicationClub, Integer> {


}
