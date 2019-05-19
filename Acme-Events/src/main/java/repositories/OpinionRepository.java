
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Integer> {


}
