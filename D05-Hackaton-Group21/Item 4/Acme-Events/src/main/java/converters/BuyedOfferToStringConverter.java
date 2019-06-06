
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.BuyedOffer;

@Component
@Transactional
public class BuyedOfferToStringConverter implements Converter<BuyedOffer, String> {

	@Override
	public String convert(final BuyedOffer buyedOffer) {
		String result;

		if (buyedOffer == null)
			result = null;
		else
			result = String.valueOf(buyedOffer.getId());

		return result;
	}

}
