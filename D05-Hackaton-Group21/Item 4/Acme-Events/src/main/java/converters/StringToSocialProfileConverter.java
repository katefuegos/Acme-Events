
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.SocialProfileService;
import domain.SocialProfile;

@Component
@Transactional
public class StringToSocialProfileConverter implements Converter<String, SocialProfile> {

	@Autowired
	SocialProfileService	socialProfileService;


	@Override
	public SocialProfile convert(final String text) {
		SocialProfile result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.socialProfileService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
