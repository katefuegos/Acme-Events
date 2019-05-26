
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Access(AccessType.PROPERTY)
public class ClubForm {

	private int		id;
	private String	reasonReject;


	@NotNull
	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@NotBlank
	public String getReasonReject() {
		return this.reasonReject;
	}

	public void setReasonReject(final String reasonReject) {
		this.reasonReject = reasonReject;
	}
}
