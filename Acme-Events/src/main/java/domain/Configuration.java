
package domain;

import java.util.Collection;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private int								varTax;
	private int								countryCode;
	private Map<String, String>				welcomeMessage;
	private String							banner;
	private Map<String, Collection<String>>	spamWords;
	private int								finderMaxResults;
	private String							systemName;
	private Collection<String>				priorities;


	@NotNull
	@ElementCollection
	public Collection<String> getPriorities() {
		return this.priorities;
	}

	public void setPriorities(final Collection<String> priorities) {
		this.priorities = priorities;
	}

	@Range(min = 0, max = 100)
	public int getVarTax() {
		return this.varTax;
	}

	public void setVarTax(final int varTax) {
		this.varTax = varTax;
	}

	@NotBlank
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	public void setFinderMaxResults(final Integer finderMaxResults) {
		this.finderMaxResults = finderMaxResults;
	}

	@Range(min = 0, max = 999)
	public int getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final int countryCode) {
		this.countryCode = countryCode;
	}

	@NotBlank
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@Range(min = 0, max = 100)
	public int getFinderMaxResults() {
		return this.finderMaxResults;
	}

	public void setFinderMaxResults(final int finderMaxResults) {
		this.finderMaxResults = finderMaxResults;
	}

	@NotEmpty
	@ElementCollection
	public Map<String, String> getWelcomeMessage() {
		return this.welcomeMessage;
	}

	public void setWelcomeMessage(final Map<String, String> welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@NotEmpty
	@ElementCollection(targetClass = org.hibernate.mapping.Collection.class)
	public Map<String, Collection<String>> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final Map<String, Collection<String>> spamWords) {
		this.spamWords = spamWords;
	}

	// Relationships ---------------------------------------------------------
}
