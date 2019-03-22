package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: testResult
 *
 */
@Entity(name="testResult")

public class TestResult implements Serializable {

	@EmbeddedId
	private TestResultPk testResultPk;
	private float score;
	private boolean passed;
	@ManyToOne
	@JoinColumn(name="testId", referencedColumnName="id",insertable=false,updatable=false)
	private Test test;
	@ManyToOne
	@JoinColumn(name="candidateId", referencedColumnName="id",insertable=false,updatable=false)
	private Candidate candidate;
	
	private static final long serialVersionUID = 1L;

	public TestResult() {
		super();
	}   
	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}   
	public boolean getPassed() {
		return this.passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

   
}
