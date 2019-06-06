
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.EventService;
import domain.Event;

@Component
@Transactional
public class StringToEventConverter implements Converter<String, Event> {

	@Autowired
	EventService	eventService;


	@Override
	public Event convert(final String text) {
		Event result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.eventService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
