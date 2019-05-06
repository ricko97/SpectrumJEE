package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: course
 *
 */
@Entity(name="course")
public class Course implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date added_at;
	@ManyToOne
	@JoinColumn(name="coach_id")
	private Coach coach;
	private static final long serialVersionUID = 1L;

	public Course(int id, Date added_at, String description) {
		super();
		this.id = id;
		this.description = description;
		this.added_at = added_at;
	}
	public Course() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Date getAdded_at() {
		return this.added_at;
	}

	public void setAdded_at(Date added_at) {
		this.added_at = added_at;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
   
}
