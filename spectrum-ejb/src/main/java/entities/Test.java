package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Test
 *
 */
@Entity
public class Test implements Serializable {

	   
	@Id
	private int id;
	private int duration;
	private Test_t type;
	private Date modified_at;
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Question>questions;
	@OneToMany(mappedBy="test")
	private List<Candidate_test>candidate_tests;
	@OneToOne(mappedBy="test")
	private Interview interview;
	
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}   
	public Date getModified_at() {
		return this.modified_at;
	}

	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Candidate_test> getCandidate_tests() {
		return candidate_tests;
	}
	public void setCandidate_tests(List<Candidate_test> candidate_tests) {
		this.candidate_tests = candidate_tests;
	}
	public Test_t getType() {
		return type;
	}
	public void setType(Test_t type) {
		this.type = type;
	}
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}

   
}
