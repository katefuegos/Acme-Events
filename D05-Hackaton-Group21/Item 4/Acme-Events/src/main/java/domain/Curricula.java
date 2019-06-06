package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String ticker;
	private String fullName;
	private String photo;
	private String email;
	private String phone;
	private String link;
	private boolean copy;
	
	// Relationships ---------------------------------------------------------

	@Column(unique = true)
	@Pattern(regexp = "^\\d{6}-(\\d?\\w){6}$")
	@NotBlank
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@NotBlank
	@URL
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotBlank
	@URL
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@NotNull
	public boolean isCopy() {
		return copy;
	}

	public void setCopy(boolean copy) {
		this.copy = copy;
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
