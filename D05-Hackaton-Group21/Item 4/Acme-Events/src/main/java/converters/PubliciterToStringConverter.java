
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Publiciter;

@Component
@Transactional
public class PubliciterToStringConverter implements Converter<Publiciter, String> {

	@Override
	public String convert(final Publiciter publiciter) {
		String result;

		if (publiciter == null)
			result = null;
		else
			result = String.valueOf(publiciter.getId());

		return result;
	}

}
