
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ClubService;
import domain.Club;

@Component
@Transactional
public class StringToClubConverter implements Converter<String, Club> {

	@Autowired
	ClubService	clubService;


	@Override
	public Club convert(final String text) {
		Club result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.clubService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
