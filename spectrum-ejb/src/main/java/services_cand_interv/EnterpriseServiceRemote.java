package services_cand_interv;


import javax.ejb.Remote;

import entities.Enterprise;
import entities.JobOffer;

@Remote
public interface EnterpriseServiceRemote {
	public int addJobOffer(Enterprise ent, JobOffer offer);
	public boolean modifyJobOffer(Enterprise ent, JobOffer offer);
	public boolean deleteJobOffer(Enterprise ent, int offerId);
	public JobOffer searchJobOffer(Enterprise ent, int offerId);
}
