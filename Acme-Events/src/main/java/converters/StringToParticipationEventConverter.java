
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ParticipationEventService;
import domain.ParticipationEvent;

@Component
@Transactional
public class StringToParticipationEventConverter implements Converter<String, ParticipationEvent> {

	@Autowired
	ParticipationEventService	participationEventService;


	@Override
	public ParticipationEvent convert(final String text) {
		ParticipationEvent result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.participationEventService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
