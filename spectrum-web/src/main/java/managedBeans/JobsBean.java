package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entities.JobOffer;
import jobservices.JobOfferService;

@ManagedBean
@SessionScoped
public class JobsBean {
	
	@EJB
	JobOfferService jobOfferService;
	
	private Integer enterpriseId=1;
	private List<JobOffer>jobOffers;
	private JobOffer selectedJob;
	
	@PostConstruct
	public void init() {
		jobOffers = jobOfferService.getjobOffersByEnt(enterpriseId);
	}

	public List<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(List<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

	public JobOffer getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(JobOffer selectedJob) {
		this.selectedJob = selectedJob;
	}
}
