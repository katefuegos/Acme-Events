
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Client extends Actor {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS

	private String	DNI;

	@NotBlank
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}
	
	
		
	// Relationships ---------------------------------------------------------

}
