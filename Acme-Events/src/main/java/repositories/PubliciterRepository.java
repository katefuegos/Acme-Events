
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Publiciter;

@Repository
public interface PubliciterRepository extends JpaRepository<Publiciter, Integer> {


}
