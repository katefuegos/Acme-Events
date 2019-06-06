
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.OpinionService;
import domain.Opinion;

@Component
@Transactional
public class StringToOpinionConverter implements Converter<String, Opinion> {

	@Autowired
	OpinionService	opinionService;


	@Override
	public Opinion convert(final String text) {
		Opinion result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.opinionService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
