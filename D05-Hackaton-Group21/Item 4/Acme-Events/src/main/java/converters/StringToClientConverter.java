
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.ClientService;
import domain.Client;

@Component
@Transactional
public class StringToClientConverter implements Converter<String, Client> {

	@Autowired
	ClientService	clientService;


	@Override
	public Client convert(final String text) {
		Client result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.clientService.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
