package entities;

import entities.Candidate_testPK;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: candidate_test
 *
 */
@Entity(name="candidate_test")

public class Candidate_test implements Serializable {

	@EmbeddedId
	private Candidate_testPK candidate_testPk;
	private boolean passed;
	private float score;
	private Date date;
	@ManyToOne
	@JoinColumn(name="testID", referencedColumnName="id",insertable=false,updatable=false)
	private Test test;
	@ManyToOne
	@JoinColumn(name="candidateID", referencedColumnName="id",insertable=false,updatable=false)
	private Candidate candidate;
	private static final long serialVersionUID = 1L;

	public Candidate_test() {
		super();
	}   
	public Candidate_testPK getCandidate_testPk() {
		return this.candidate_testPk;
	}

	public void setCandidate_testPk(Candidate_testPK candidate_testPk) {
		this.candidate_testPk = candidate_testPk;
	}   
	public boolean getPassed() {
		return this.passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}   
	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
   
}
