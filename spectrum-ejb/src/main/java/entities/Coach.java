package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: coachProfile
 *
 */
@Entity(name="coach")
public class Coach implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String bio;
	@OneToOne(cascade=CascadeType.ALL)
	private User user;
	@OneToMany
	private List<Skill>skills;
	@OneToMany(targetEntity=Course.class, cascade=CascadeType.ALL)
	private List<Course>courses;
	@OneToMany(targetEntity=Coaching.class, cascade=CascadeType.ALL)
	private List<Coaching>coachings;
	private static final long serialVersionUID = 1L;

	public Coach() {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Coaching> getCoachings() {
		return coachings;
	}
	public void setCoachings(List<Coaching> coachings) {
		this.coachings = coachings;
	}
   
}
