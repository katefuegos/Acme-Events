
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.OfferService;
import domain.Offer;

@Component
@Transactional
public class StringToOfferConverter implements Converter<String, Offer> {

	@Autowired
	OfferService	offerService;


	@Override
	public Offer convert(final String text) {
		Offer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.offerService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
