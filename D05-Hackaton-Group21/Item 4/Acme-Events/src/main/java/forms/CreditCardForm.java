
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Access(AccessType.PROPERTY)
public class CreditCardForm {

	// Attributes------------------------------------------------------------------

	private int		id;
	private int		version;

	private String	holderName;
	private String	brandName;
	private String	number;
	private String	expirationMonth;
	private String	expirationYear;
	private String	CVVCode;


	// Constructor------------------------------------------------------------------

	public CreditCardForm() {
		super();
	}

	// Getter and
	// Setters------------------------------------------------------------

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
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@Pattern(regexp = "^(VISA|MASTER|DINNERS|AMEX)$")
	@NotBlank
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	@CreditCardNumber
	@NotBlank
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@Range(min = 1, max = 12)
	public String getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Range(min = 2019, max = 3000)
	public String getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final String expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public String getCVVCode() {
		return this.CVVCode;
	}

	public void setCVVCode(final String cVVCode) {
		this.CVVCode = cVVCode;
	}

}
