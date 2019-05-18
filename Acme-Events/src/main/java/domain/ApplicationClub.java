package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ApplicationClub extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String commentary;
	private Date momentSubmitted;
	private Date momentAccepted;
	private Date momentRejected;
	private String reasonReject;
	
	
	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentSubmitted() {
		return momentSubmitted;
	}

	public void setMomentSubmitted(Date momentSubmitted) {
		this.momentSubmitted = momentSubmitted;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentAccepted() {
		return momentAccepted;
	}

	public void setMomentAccepted(Date momentAccepted) {
		this.momentAccepted = momentAccepted;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Past
	public Date getMomentRejected() {
		return momentRejected;
	}

	public void setMomentRejected(Date momentRejected) {
		this.momentRejected = momentRejected;
	}

	public String getReasonReject() {
		return reasonReject;
	}

	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}

	// Relationships ---------------------------------------------------------

	private Publiciter publiciter;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Publiciter getPubliciter() {
		return this.publiciter;
	}

	public void setPubliciter(final Publiciter publiciter) {
		this.publiciter = publiciter;
	}
}
