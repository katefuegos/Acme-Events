
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.AdministratorService;
import domain.Administrator;

@Component
@Transactional
public class StringToAdministratorConverter implements Converter<String, Administrator> {

	@Autowired
	AdministratorService	administratorService;


	@Override
	public Administrator convert(final String text) {
		Administrator result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.administratorService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
