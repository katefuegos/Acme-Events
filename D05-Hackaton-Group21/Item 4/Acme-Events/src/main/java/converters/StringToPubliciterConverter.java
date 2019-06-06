
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.PubliciterService;
import domain.Publiciter;

@Component
@Transactional
public class StringToPubliciterConverter implements Converter<String, Publiciter> {

	@Autowired
	PubliciterService	publiciterService;


	@Override
	public Publiciter convert(final String text) {
		Publiciter result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.publiciterService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
