
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.JobOffer;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {


}
