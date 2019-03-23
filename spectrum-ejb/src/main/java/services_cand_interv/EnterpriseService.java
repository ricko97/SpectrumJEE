package services_cand_interv;


import javax.ejb.Stateless;

import entities.Enterprise;
import entities.JobOffer;

@Stateless
public class EnterpriseService implements EnterpriseServiceRemote{

	
	@Override
	public int addJobOffer(Enterprise ent, JobOffer offer) {
		if (this.searchJobOffer(ent, offer.getId())==null) {
			ent.getJobOffers().add(offer);
			return offer.getId();
		}else
			return 0;
		
	}

	@Override
	public boolean modifyJobOffer(Enterprise ent, JobOffer offer) {
		JobOffer off = this.searchJobOffer(ent, offer.getId());
		if (off!=null) {
			off.setEnd(offer.getEnd());
			off.setDescription(offer.getDescription());
			return true;
		}else
			return false;
	}

	@Override
	public boolean deleteJobOffer(Enterprise ent, int offerId) {
		JobOffer offer = this.searchJobOffer(ent, offerId);
		if (offer!=null) {
			ent.getJobOffers().remove(offer);
			return true;
		}else
			return false;
	}

	@Override
	public JobOffer searchJobOffer(Enterprise ent, int offerId) {
		for (JobOffer offer : ent.getJobOffers()) {
			if (offer.getId()==offerId)
				return offer;
		}
		return null;
	}


}
