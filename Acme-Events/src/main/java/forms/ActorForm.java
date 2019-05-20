
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Access(AccessType.PROPERTY)
public class ActorForm {

	// Attributes------------------------------------------------------------------

	private int			id;
	private int			version;

	private UserAccount	userAccount;
	private String		confirmacion;
	private boolean		checkTerms;
	private String		auth;

	private String		name;
	private String		middleName;
	private String		surname;
	private String		photo;
	private String		email;
	private String		phone;
	private String		address;
	private Boolean		isSuspicious;
	private boolean		isBanned;

	private String		DNI;


	// Constructor------------------------------------------------------------------

	public ActorForm() {
		super();
	}

	// Getter and
	// Setters------------------------------------------------------------

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(final String auth) {
		this.auth = auth;
	}

	public boolean getCheckTerms() {
		return this.checkTerms;
	}

	public void setCheckTerms(final boolean checkTerms) {
		this.checkTerms = checkTerms;
	}

	@NotNull
	@Valid
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getConfirmacion() {
		return this.confirmacion;
	}

	public void setConfirmacion(final String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@NotBlank
	public String getDNI() {
		return this.DNI;
	}

	public void setDNI(final String DNI) {
		this.DNI = DNI;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Boolean getIsSuspicious() {
		return this.isSuspicious;
	}

	public void setIsSuspicious(final Boolean isSuspicious) {
		this.isSuspicious = isSuspicious;
	}

	@NotNull
	public boolean getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(final boolean isBanned) {
		this.isBanned = isBanned;
	}

}
