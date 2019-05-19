
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ProfessionalRecordService;
import domain.ProfessionalRecord;

@Component
@Transactional
public class StringToProfessionalRecordConverter implements Converter<String, ProfessionalRecord> {

	@Autowired
	ProfessionalRecordService	professionalRecordService;


	@Override
	public ProfessionalRecord convert(final String text) {
		ProfessionalRecord result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.professionalRecordService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
