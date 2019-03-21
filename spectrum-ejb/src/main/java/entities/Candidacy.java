package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: candidacy
 *
 */
@Entity(name="candidacy")
public class Candidacy implements Serializable {

	@EmbeddedId  
	private CandidacyPk candidacyPk;
	private Date date;
	private CandidacyStatus status;
	@ManyToOne
	@JoinColumn(name="jobID", referencedColumnName="id",insertable=false,updatable=false)
	private JobOffer joboffer;
	@ManyToOne
	@JoinColumn(name="candidateID", referencedColumnName="id",insertable=false,updatable=false)
	private Candidate candidate;
	private static final long serialVersionUID = 1L;

	public Candidacy() {
		super();
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public JobOffer getJoboffer() {
		return joboffer;
	}
	public void setJoboffer(JobOffer joboffer) {
		this.joboffer = joboffer;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public CandidacyPk getCandidacyPk() {
		return candidacyPk;
	}
	public void setCandidacyPk(CandidacyPk candidacyPk) {
		this.candidacyPk = candidacyPk;
	}
	public CandidacyStatus getStatus() {
		return status;
	}
	public void setStatus(CandidacyStatus status) {
		this.status = status;
	}
   
}
