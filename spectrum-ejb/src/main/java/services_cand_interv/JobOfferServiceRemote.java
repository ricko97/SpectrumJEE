package services_cand_interv;

import java.util.List;

import javax.ejb.Remote;

import entities.JobOffer;

@Remote
public interface JobOfferServiceRemote {
	public boolean modifyJobOffer(int entId, JobOffer offer);
	public JobOffer searchJobOffer(int offerId);
	public List<JobOffer>getJobOffers();
	public List<JobOffer>getjobOffersByEnt(int EntId);
	public int addJobOffer(int entId, JobOffer offer);
	public boolean removeJobOfferFromEnt(int entId, int offerId);
	public JobOffer searchJobOfferInEnt(int entId, int offerId);
}
