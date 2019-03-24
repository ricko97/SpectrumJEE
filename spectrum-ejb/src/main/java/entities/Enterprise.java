package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: enterpriseProfile
 *
 */
@Entity(name="enterprise")
public class Enterprise implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String activity;
	private String domain;
	@OneToOne
	private User user;
	@OneToMany(targetEntity = JobOffer.class, mappedBy= "enterprise", cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER)
	private List<JobOffer>jobOffers;
	@OneToMany(targetEntity=Event.class,cascade=CascadeType.ALL)
	private List<Event>events;
	@OneToMany(targetEntity=Test.class, cascade = CascadeType.ALL)
	private List<Test>tests;
	private static final long serialVersionUID = 1L;

	public Enterprise() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}   
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<JobOffer> getJobOffers() {
		return jobOffers;
	}
	public void setJobOffers(List<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
   
}
