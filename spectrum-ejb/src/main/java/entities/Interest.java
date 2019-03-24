package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: interest
 *
 */
@Entity(name="interest")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Interest implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	public Interest() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
   
}
