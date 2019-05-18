package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String ticker;
	private String title;
	private String description;
	private Double price;
	private boolean draftMode;
	private Date momentPublished;

	@NotBlank
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Range(min = 0)
	@Digits(integer = 2, fraction = 2)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@NotNull
	public boolean isDraftMode() {
		return draftMode;
	}

	public void setDraftMode(boolean draftMode) {
		this.draftMode = draftMode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentPublished() {
		return momentPublished;
	}

	public void setMomentPublished(Date momentPublished) {
		this.momentPublished = momentPublished;
	}

	// Relationships ---------------------------------------------------------

	private Collection<BuyedOffer> buyedOffers;
	private Publiciter publiciter;
	private Event Event;

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<BuyedOffer> getBuyedOffers() {
		return buyedOffers;
	}

	public void setBuyedOffers(Collection<BuyedOffer> buyedOffers) {
		this.buyedOffers = buyedOffers;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Event getEvent() {
		return Event;
	}

	public void setEvent(Event event) {
		Event = event;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Publiciter getPubliciter() {
		return publiciter;
	}

	public void setPubliciter(Publiciter publiciter) {
		this.publiciter = publiciter;
	}

}
