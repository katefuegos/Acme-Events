
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.BuyedOfferService;
import domain.BuyedOffer;

@Component
@Transactional
public class StringToBuyedOfferConverter implements Converter<String, BuyedOffer> {

	@Autowired
	BuyedOfferService	buyedOfferService;


	@Override
	public BuyedOffer convert(final String text) {
		BuyedOffer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.buyedOfferService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
