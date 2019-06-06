
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ApplicationJob;

@Repository
public interface ApplicationJobRepository extends JpaRepository<ApplicationJob, Integer> {


}
