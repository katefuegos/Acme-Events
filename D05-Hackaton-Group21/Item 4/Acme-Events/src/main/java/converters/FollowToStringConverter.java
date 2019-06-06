
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Follow;

@Component
@Transactional
public class FollowToStringConverter implements Converter<Follow, String> {

	@Override
	public String convert(final Follow follow) {
		String result;

		if (follow == null)
			result = null;
		else
			result = String.valueOf(follow.getId());

		return result;
	}

}
