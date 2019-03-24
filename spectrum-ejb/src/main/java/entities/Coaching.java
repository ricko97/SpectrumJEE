package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Coaching
 *
 */
@Entity
public class Coaching implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private Date start;
	private Date end;
	@OneToMany(targetEntity=Workshop.class, cascade=CascadeType.ALL)
	private List<Workshop>workshops;
	@ManyToOne
	@JoinColumn(name="coach_id")
	private Coach coach;
	private static final long serialVersionUID = 1L;

	public Coaching() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public Date getStart() {
		return this.start;
	}

	public void setStart(Date start) {
		this.start = start;
	}   
	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	public List<Workshop> getWorkshops() {
		return workshops;
	}
	public void setWorkshops(List<Workshop> workshops) {
		this.workshops = workshops;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
   
}
