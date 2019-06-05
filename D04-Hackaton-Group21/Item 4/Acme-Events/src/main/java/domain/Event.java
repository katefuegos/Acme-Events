
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String	ticker;
	private String	title;
	private Date	momentPublished;
	private String	poster;
	private String	description;
	private String	address;
	private Double	price;
	private Date	momentStart;
	private Date	momentEnd;
	private String	status;
	private boolean	draftMode;

	private Double	score;


	public Double getScore() {
		return this.score;
	}

	public void setScore(final Double score) {
		this.score = score;
	}

	@Column(unique = true)
	@Pattern(regexp = "^\\d{6}-(\\d?\\w){6}$")
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentPublished() {
		return this.momentPublished;
	}

	public void setMomentPublished(final Date momentPublished) {
		this.momentPublished = momentPublished;
	}

	@URL
	public String getPoster() {
		return this.poster;
	}

	public void setPoster(final String poster) {
		this.poster = poster;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotNull
	@Range(min = 0)
	@Digits(integer = 9, fraction = 2)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getMomentStart() {
		return this.momentStart;
	}

	public void setMomentStart(final Date momentStart) {
		this.momentStart = momentStart;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getMomentEnd() {
		return this.momentEnd;
	}

	public void setMomentEnd(final Date momentEnd) {
		this.momentEnd = momentEnd;
	}

	@Pattern(regexp = "^(AVAILABLE|CANCELLED)$")
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@NotNull
	public boolean isDraftMode() {
		return this.draftMode;
	}

	public void setDraftMode(final boolean draftMode) {
		this.draftMode = draftMode;
	}


	// Relationships ---------------------------------------------------------

	private Category						category;
	private Club							club;
	private Collection<Opinion>				opinions;
	private Collection<ParticipationEvent>	participationsEvent;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Club getClub() {
		return this.club;
	}

	public void setClub(final Club club) {
		this.club = club;
	}

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<Opinion> getOpinions() {
		return this.opinions;
	}

	public void setOpinions(final Collection<Opinion> opinions) {
		this.opinions = opinions;
	}

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<ParticipationEvent> getParticipationsEvent() {
		return this.participationsEvent;
	}

	public void setParticipationsEvent(final Collection<ParticipationEvent> participationsEvent) {
		this.participationsEvent = participationsEvent;
	}

}
