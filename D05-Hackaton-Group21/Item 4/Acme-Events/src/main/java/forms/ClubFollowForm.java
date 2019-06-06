
package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;

@Access(AccessType.PROPERTY)
public class ClubFollowForm {

	// Constructor ---------------------------------------------------
	public ClubFollowForm() {
		super();
	}


	// Attributes -----------------------------------------------------
	private String	nameClub;
	private Date	momentFollow;
	private int		idFollow;


	public String getNameClub() {
		return this.nameClub;
	}

	public void setNameClub(final String nameClub) {
		this.nameClub = nameClub;
	}

	public Date getMomentFollow() {
		return this.momentFollow;
	}

	public void setMomentFollow(final Date momentFollow) {
		this.momentFollow = momentFollow;
	}

	public int getIdFollow() {
		return this.idFollow;
	}

	public void setIdFollow(final int idFollow) {
		this.idFollow = idFollow;
	}

}
