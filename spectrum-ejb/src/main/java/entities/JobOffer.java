package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
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
	private String title;
	private String description;
	@OneToMany(mappedBy="joboffer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Candidacy>candidacies = new ArrayList<Candidacy>();
	@ManyToOne
	private Enterprise enterprise;
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
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidacies == null) ? 0 : candidacies.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + id;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobOffer other = (JobOffer) obj;
		if (candidacies == null) {
			if (other.candidacies != null)
				return false;
		} else if (!candidacies.equals(other.candidacies))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id != other.id)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
   
}
