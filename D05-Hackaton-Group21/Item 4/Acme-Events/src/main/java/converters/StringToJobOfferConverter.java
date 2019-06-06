
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.JobOfferService;
import domain.JobOffer;

@Component
@Transactional
public class StringToJobOfferConverter implements Converter<String, JobOffer> {

	@Autowired
	JobOfferService	jobOfferService;


	@Override
	public JobOffer convert(final String text) {
		JobOffer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.jobOfferService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
