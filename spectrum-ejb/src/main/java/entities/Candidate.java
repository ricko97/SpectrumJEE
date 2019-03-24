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
	@OneToOne
	private User user;
	private String bio;
	@OneToMany
	private List<Skill>skills;
	@OneToMany(targetEntity=Interest.class,cascade=CascadeType.ALL)
	private List<Interest>interests;
	@OneToMany(mappedBy="candidate")
	private List<Candidacy>candidacies;
	@OneToMany(mappedBy="candidate", cascade = CascadeType.REMOVE)
	private List<TestResult>testResults;
	
	
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

}
