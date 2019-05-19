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
public class Event extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String ticker;
	private Date momentPublished;
	private String description;
	private String adress;
	private Double price;
	private Date momentStart;
	private Date momentEnd;
	private String status;
	private boolean draftMode;

	@NotBlank
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
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

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@NotNull
	@Range(min = 0)
	@Digits(integer = 9, fraction = 2)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentStart() {
		return momentStart;
	}

	public void setMomentStart(Date momentStart) {
		this.momentStart = momentStart;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentEnd() {
		return momentEnd;
	}

	public void setMomentEnd(Date momentEnd) {
		this.momentEnd = momentEnd;
	}

	@NotBlank
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@NotNull
	public boolean isDraftMode() {
		return draftMode;
	}

	public void setDraftMode(boolean draftMode) {
		this.draftMode = draftMode;
	}

	// Relationships ---------------------------------------------------------

	private Category category;
	private Club club;
	private Collection<Opinion> opinions;
	private Collection<ParticipationEvent> participationsEvent;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(Collection<Opinion> opinions) {
		this.opinions = opinions;
	}

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<ParticipationEvent> getParticipationsEvent() {
		return participationsEvent;
	}

	public void setParticipationsEvent(
			Collection<ParticipationEvent> participationsEvent) {
		this.participationsEvent = participationsEvent;
	}

}
