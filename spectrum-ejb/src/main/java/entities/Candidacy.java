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
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="jobID", referencedColumnName="id",insertable=false,updatable=false)
	private JobOffer joboffer;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="candidateID", referencedColumnName="id",insertable=false,updatable=false)
	private Candidate candidate;
	@Transient
	private String name ;
	@Transient
	private String email;
	@Transient
	private String birth;
	@Transient
	private String offerTitle;
	@Transient
	private Date offerEnd;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getOfferTitle() {
		return offerTitle;
	}
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
	public Date getOfferEnd() {
		return offerEnd;
	}
	public void setOfferEnd(Date offerEnd) {
		this.offerEnd = offerEnd;
	}

   
}
