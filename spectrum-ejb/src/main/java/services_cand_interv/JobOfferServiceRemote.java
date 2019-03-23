package services_cand_interv;

import java.util.List;

import javax.ejb.Remote;

import entities.JobOffer;

@Remote
public interface JobOfferServiceRemote {
	public int addJobOffer(JobOffer offer);
	public boolean modifyJobOffer(JobOffer offer);
	public boolean deleteJobOffer(int offerId);
	public JobOffer searchJobOffer(int offerId);
	public List<JobOffer>getJobOffers();
}
