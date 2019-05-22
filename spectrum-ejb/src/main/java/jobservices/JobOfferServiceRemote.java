package jobservices;

import java.util.List;

import javax.ejb.Remote;

import entities.Enterprise;
import entities.JobOffer;

@Remote
public interface JobOfferServiceRemote {
	public void modifyJobOffer(JobOffer offer);
	public JobOffer searchJobOffer(int offerId);
	public List<JobOffer>getJobOffers();
	public List<JobOffer>getjobOffersByEnt(int EntId);
	public JobOffer getjobOfferByTitle(String title, int entId);
	public void addJobOffer(int entId, JobOffer offer);
	public void removeJobOffer(int offerId);
	public JobOffer searchJobOfferInEnt(int entId, int offerId);
	public Enterprise getEntById(int entId);
}
