package services_cand_interv;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.JobOffer;

@Stateless
public class JobOfferService implements JobOfferServiceRemote{

	@PersistenceContext(unitName="spectrum-ejb")
	EntityManager em;
	
	public int addJobOffer(JobOffer offer) {
		em.persist(offer);
		return offer.getId();
	}

	@Override
	public boolean modifyJobOffer(JobOffer offer) {
		JobOffer joboffer = em.find(JobOffer.class, offer.getId());
		if (joboffer!=null) {
			joboffer.setEnd(offer.getEnd());
			joboffer.setDescription(offer.getDescription());
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteJobOffer(int offerId) {
		JobOffer joboffer = em.find(JobOffer.class, offerId);
		if (joboffer!=null) {
			em.remove(joboffer);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public JobOffer searchJobOffer(int offerId) {
		return em.find(JobOffer.class, offerId);
	}

	@Override
	public List<JobOffer> getJobOffers() {
		return em.createQuery("select j from joboffer j", JobOffer.class).getResultList();
	}

}
