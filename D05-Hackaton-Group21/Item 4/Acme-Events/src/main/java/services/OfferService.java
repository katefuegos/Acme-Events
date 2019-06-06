
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import domain.Offer;

@Service
@Transactional
public class OfferService {

	// Repository-----------------------------------------------

	@Autowired
	private OfferRepository			offerRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public OfferService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Offer create(final String authority) {
		final Offer offer = new Offer();
		
		return offer;
	}

	public List<Offer> findAll() {
		return this.offerRepository.findAll();
	}

	public Offer findOne(final Integer offerId) {
		return this.offerRepository.findOne(offerId);
	}

	public Offer save(final Offer offer) {
		Assert.notNull(offer);
		final Offer saved = this.offerRepository.save(offer);
		return saved;
	}

	public void delete(final Offer offer) {
		this.offerRepository.delete(offer);
	}

	// Other Methods--------------------------------------------
}
