
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ApplicationClub;

@Component
@Transactional
public class ApplicationClubToStringConverter implements Converter<ApplicationClub, String> {

	@Override
	public String convert(final ApplicationClub applicationClub) {
		String result;

		if (applicationClub == null)
			result = null;
		else
			result = String.valueOf(applicationClub.getId());

		return result;
	}

}
