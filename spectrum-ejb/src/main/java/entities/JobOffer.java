package Entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: jobOffer
 *
 */
@Entity(name="jobOffer")
public class JobOffer implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date start;
	private Date end;
	private String description;
	@OneToMany(mappedBy="joboffer")
	private List<Candidacy>candidacies;
	private static final long serialVersionUID = 1L;

	public JobOffer() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public List<Candidacy> getCandidacies() {
		return candidacies;
	}
	public void setCandidacies(List<Candidacy> candidacies) {
		this.candidacies = candidacies;
	}
   
}
