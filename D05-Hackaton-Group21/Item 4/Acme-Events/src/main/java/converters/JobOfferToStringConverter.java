
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.JobOffer;

@Component
@Transactional
public class JobOfferToStringConverter implements Converter<JobOffer, String> {

	@Override
	public String convert(final JobOffer jobOffer) {
		String result;

		if (jobOffer == null)
			result = null;
		else
			result = String.valueOf(jobOffer.getId());

		return result;
	}

}
