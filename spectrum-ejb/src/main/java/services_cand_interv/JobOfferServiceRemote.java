package services_cand_interv;

import java.util.List;

import javax.ejb.Remote;

import entities.JobOffer;

@Remote
public interface JobOfferServiceRemote {
	public int addJobOffer(JobOffer offer);
	public void modifyJobOffer(int offerId);
	public void deleteJobOffer(int offerId);
	public boolean searchJobOffer(int offerId);
	public List<JobOffer>getJobOffers();
}
