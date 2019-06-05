
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ApplicationJobService;
import domain.ApplicationJob;

@Component
@Transactional
public class StringToApplicationJobConverter implements Converter<String, ApplicationJob> {

	@Autowired
	ApplicationJobService	applicationJobService;


	@Override
	public ApplicationJob convert(final String text) {
		ApplicationJob result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.applicationJobService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
