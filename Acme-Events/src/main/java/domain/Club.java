
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)

public class Club extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String	name;
	private String 	address;
	private String 	description;
	private String 	pictures;
	private boolean	accepted;
	private boolean	draftMode;
	private String 	reasonReject;
	
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@URL
	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	@NotNull
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@NotNull
	public boolean isDraftMode() {
		return draftMode;
	}

	public void setDraftMode(boolean draftMode) {
		this.draftMode = draftMode;
	}

	public String getReasonReject() {
		return reasonReject;
	}

	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}

	// Relationships ---------------------------------------------------------
	private Manager			manager;
	private Collection<ApplicationClub> applicationsClub;
	private Collection<Follow> follows;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}
	
	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<ApplicationClub> getApplicationsClub() {
		return applicationsClub;
	}

	public void setApplicationsClub(Collection<ApplicationClub> applicationsClub) {
		this.applicationsClub = applicationsClub;
	}

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<Follow> getFollows() {
		return follows;
	}

	public void setFollows(Collection<Follow> follows) {
		this.follows = follows;
	}
}
