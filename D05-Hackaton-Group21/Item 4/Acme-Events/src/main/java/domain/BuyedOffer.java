package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class BuyedOffer extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private Date moment;
	private String creditCardNumber;

	@NotBlank
	@CreditCardNumber
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMoment() {
		return this.moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	// Relationships ---------------------------------------------------------

	private Client client;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(final Client client) {
		this.client = client;
	}
}
