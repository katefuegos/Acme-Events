
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ApplicationClubService;
import domain.ApplicationClub;

@Component
@Transactional
public class StringToApplicationClubConverter implements Converter<String, ApplicationClub> {

	@Autowired
	ApplicationClubService	applicationClubService;


	@Override
	public ApplicationClub convert(final String text) {
		ApplicationClub result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.applicationClubService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
