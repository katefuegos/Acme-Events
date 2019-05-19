
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.FollowService;
import domain.Follow;

@Component
@Transactional
public class StringToFollowConverter implements Converter<String, Follow> {

	@Autowired
	FollowService	followService;


	@Override
	public Follow convert(final String text) {
		Follow result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.followService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
