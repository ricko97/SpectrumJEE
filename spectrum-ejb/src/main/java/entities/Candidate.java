package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: candidateProfile
 *
 */
@Entity(name="candidate")
public class Candidate implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(mappedBy="candidate")
	private User user;
	private String bio;
	@OneToMany
	private List<Skill>skills;
	@OneToMany
	private List<Interest>interests;
	@OneToMany(mappedBy="candidate")
	private List<Candidate_test>candidate_tests;
	@OneToMany(mappedBy="candidate")
	private List<Candidacy>candidacies;
	
	
	private static final long serialVersionUID = 1L;

	public Candidate() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public List<Interest> getInterests() {
		return interests;
	}
	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
   
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Candidacy> getCandidacies() {
		return candidacies;
	}
	public void setCandidacies(List<Candidacy> candidacies) {
		this.candidacies = candidacies;
	}
	public List<Candidate_test> getCandidate_tests() {
		return candidate_tests;
	}
	public void setCandidate_tests(List<Candidate_test> candidate_tests) {
		this.candidate_tests = candidate_tests;
	}
}
