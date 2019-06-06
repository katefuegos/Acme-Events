
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ApplicationJob;

@Component
@Transactional
public class ApplicationJobToStringConverter implements Converter<ApplicationJob, String> {

	@Override
	public String convert(final ApplicationJob applicationJob) {
		String result;

		if (applicationJob == null)
			result = null;
		else
			result = String.valueOf(applicationJob.getId());

		return result;
	}

}
