
package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Event;

@Access(AccessType.PROPERTY)
public class OpinionForm3 {

	// Constructor ---------------------------------------------------
	public OpinionForm3() {
		super();
	}


	// Attributes -----------------------------------------------------
	private String	title;
	private String	description;
	private int		score;
	private Event	event;
	private String	titleEvent;
	private Date	moment;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Range(min = 0, max = 10)
	public int getScore() {
		return this.score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	@Valid
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}

	@NotBlank
	public String getTitleEvent() {
		return this.titleEvent;
	}

	public void setTitleEvent(final String titleEvent) {
		this.titleEvent = titleEvent;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

}
