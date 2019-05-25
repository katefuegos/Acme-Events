
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Access(AccessType.PROPERTY)

public class ClubForm {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private int	id;
	private String 	reasonReject;
	
	@NotNull
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getReasonReject() {
		return reasonReject;
	}

	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}
}
