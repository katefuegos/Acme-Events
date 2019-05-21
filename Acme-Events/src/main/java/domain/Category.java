
package domain;

import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private Map<String, String>	title;
	private boolean				root;


	// Relationships ---------------------------------------------------------

	@NotEmpty
	@ElementCollection
	public Map<String, String> getTitle() {
		return this.title;
	}

	public void setTitle(final Map<String, String> title) {
		this.title = title;
	}

	@NotNull
	public boolean isRoot() {
		return this.root;
	}

	public void setRoot(final boolean root) {
		this.root = root;
	}


	// Relationships ---------------------------------------------------------
	private Category	rootCategory;


	@Valid
	@OneToOne(optional = true)
	public Category getRootCategory() {
		return this.rootCategory;
	}

	public void setRootCategory(final Category rootCategory) {
		this.rootCategory = rootCategory;
	}

}
