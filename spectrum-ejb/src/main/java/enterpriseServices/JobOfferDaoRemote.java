package enterpriseServices;

import java.util.List;

import javax.ejb.Remote;

import entities.JobOffer;

@Remote
public interface JobOfferDaoRemote {
	public void addJobOffer(JobOffer e);
	public void updateJobOffer(JobOffer e);
	public JobOffer findJobOffer(int id);
	public void deleteJobOffer(JobOffer e);
	public List<JobOffer> addJobOffer();

}
