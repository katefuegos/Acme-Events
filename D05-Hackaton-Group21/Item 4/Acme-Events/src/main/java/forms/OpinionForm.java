
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Access(AccessType.PROPERTY)
public class OpinionForm {

	// Constructor ---------------------------------------------------
	public OpinionForm() {
		super();
	}


	// Attributes -----------------------------------------------------
	private String	title;
	private String	description;
	private int	score;


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


	//-----------------------------------------------
	private domain.Event	event;


	@NotNull
	public domain.Event getEvent() {
		return this.event;
	}

	public void setEvent(final domain.Event event) {
		this.event = event;
	}

}
