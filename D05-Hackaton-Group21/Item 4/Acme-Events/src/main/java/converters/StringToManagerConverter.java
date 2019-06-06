
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ManagerService;
import domain.Manager;

@Component
@Transactional
public class StringToManagerConverter implements Converter<String, Manager> {

	@Autowired
	ManagerService	managerService;


	@Override
	public Manager convert(final String text) {
		Manager result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.managerService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
