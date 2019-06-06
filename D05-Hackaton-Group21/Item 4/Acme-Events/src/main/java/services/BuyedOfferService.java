package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BuyedOfferRepository;
import domain.BuyedOffer;

@Service
@Transactional
public class BuyedOfferService {

	// Repository-----------------------------------------------

	@Autowired
	private BuyedOfferRepository buyedOfferRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public BuyedOfferService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public BuyedOffer create(final String authority) {
		final BuyedOffer buyedOffer = new BuyedOffer();

		return buyedOffer;
	}

	public List<BuyedOffer> findAll() {
		return this.buyedOfferRepository.findAll();
	}

	public BuyedOffer findOne(final Integer buyedOfferId) {
		return this.buyedOfferRepository.findOne(buyedOfferId);
	}

	public BuyedOffer save(final BuyedOffer buyedOffer) {
		Assert.notNull(buyedOffer);
		final BuyedOffer saved = this.buyedOfferRepository
				.save(buyedOffer);
		return saved;
	}

	public void delete(final BuyedOffer buyedOffer) {
		this.buyedOfferRepository.delete(buyedOffer);
	}

	// Other Methods--------------------------------------------
}
