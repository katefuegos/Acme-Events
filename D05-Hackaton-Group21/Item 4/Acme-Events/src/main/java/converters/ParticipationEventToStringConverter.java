
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ParticipationEvent;

@Component
@Transactional
public class ParticipationEventToStringConverter implements Converter<ParticipationEvent, String> {

	@Override
	public String convert(final ParticipationEvent participationEvent) {
		String result;

		if (participationEvent == null)
			result = null;
		else
			result = String.valueOf(participationEvent.getId());

		return result;
	}

}
