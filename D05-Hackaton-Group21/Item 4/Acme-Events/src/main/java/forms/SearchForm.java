
package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Access(AccessType.PROPERTY)
public class SearchForm {

	// Constructor ---------------------------------------------------
	public SearchForm() {
		super();
	}


	// Attributes -----------------------------------------------------
	private String	keyWord;
	private Double	priceMin;
	private Double	priceMax;
	private Date	dateMin;
	private Date	dateMax;


	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public Double getPriceMin() {
		return this.priceMin;
	}

	public void setPriceMin(final Double priceMin) {
		this.priceMin = priceMin;
	}

	public Double getPriceMax() {
		return this.priceMax;
	}

	public void setPriceMax(final Double priceMax) {
		this.priceMax = priceMax;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getDateMin() {
		return this.dateMin;
	}

	public void setDateMin(final Date dateMin) {
		this.dateMin = dateMin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getDateMax() {
		return this.dateMax;
	}

	public void setDateMax(final Date dateMax) {
		this.dateMax = dateMax;
	}

}
