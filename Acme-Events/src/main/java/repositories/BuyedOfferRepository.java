
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.BuyedOffer;

@Repository
public interface BuyedOfferRepository extends JpaRepository<BuyedOffer, Integer> {


}
