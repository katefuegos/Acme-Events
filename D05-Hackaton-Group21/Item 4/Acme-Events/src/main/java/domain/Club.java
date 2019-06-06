package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(name = "I1", columnList = "draftMode"),
		@Index(name = "I2", columnList = "accepted"),
		@Index(name = "I3", columnList = "manager"),
		@Index(name = "I4", columnList = "reasonReject")
})
public class Club extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String name;
	private String address;
	private String description;
	private String pictures;
	private boolean accepted;
	private boolean draftMode;
	private String reasonReject;

	private Double score;

	public Double getScore() {
		return this.score;
	}

	public void setScore(final Double score) {
		this.score = score;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	public String getPictures() {
		return this.pictures;
	}

	public void setPictures(final String pictures) {
		this.pictures = pictures;
	}

	@NotNull
	public boolean isAccepted() {
		return this.accepted;
	}

	public void setAccepted(final boolean accepted) {
		this.accepted = accepted;
	}

	@NotNull
	public boolean isDraftMode() {
		return this.draftMode;
	}

	public void setDraftMode(final boolean draftMode) {
		this.draftMode = draftMode;
	}

	public String getReasonReject() {
		return this.reasonReject;
	}

	public void setReasonReject(final String reasonReject) {
		this.reasonReject = reasonReject;
	}

	// Relationships ---------------------------------------------------------
	private Manager manager;
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
		return this.applicationsClub;
	}

	public void setApplicationsClub(
			final Collection<ApplicationClub> applicationsClub) {
		this.applicationsClub = applicationsClub;
	}

	@NotNull
	@ElementCollection
	@Valid
	@OneToMany()
	public Collection<Follow> getFollows() {
		return this.follows;
	}

	public void setFollows(final Collection<Follow> follows) {
		this.follows = follows;
	}
}
