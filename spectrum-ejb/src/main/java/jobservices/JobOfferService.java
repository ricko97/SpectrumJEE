package jobservices;

import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Enterprise;
import entities.JobOffer;

@Stateless
public class JobOfferService implements JobOfferServiceRemote{

	@PersistenceContext(unitName="spectrum-ejb")
	EntityManager em;
	
	@Override
	public boolean modifyJobOffer(int entId, JobOffer offer) {
		if (this.searchJobOfferInEnt(entId, offer.getId())!=null) {
			em.merge(offer);
			return true;
		}else
			return false;
	}

	@Override
	public JobOffer searchJobOffer(int offerId) {
		return em.find(JobOffer.class, offerId);
	}

	@Override
	public List<JobOffer> getJobOffers() {
		return em.createQuery("SELECT j FROM jobOffer j", JobOffer.class).getResultList();
	}
	
	
	@Override
	public int addJobOffer(int entId, JobOffer offer) {
		if (this.searchJobOfferInEnt(entId, offer.getId())==null) {
			Enterprise enterprise = em.find(Enterprise.class, entId);
			offer.setEnterprise(enterprise);
			em.persist(offer);
			return offer.getId();
		}else
			return 0;
		
	}


	@Override
	public boolean removeJobOfferFromEnt(int entId, int offerId) {
		JobOffer offer = this.searchJobOfferInEnt(entId, offerId);
		if (offer!=null) {
			em.find(Enterprise.class, entId).getJobOffers().remove(offer);
			em.remove(offer);
			return true;
		}else
			return false;
	}

	@Override
	public JobOffer searchJobOfferInEnt(int entId, int offerId) {
		for (JobOffer off : em.find(Enterprise.class, entId).getJobOffers()) {
			if (off.getId()==offerId)
				return off;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobOffer> getjobOffersByEnt(int entId) {
		Enterprise ent = em.find(Enterprise.class, entId);
		Query query = em.createQuery("select o from jobOffer o where o.enterprise =:e");
		query.setParameter("e", ent);
		
		return query.getResultList();
	}

	@Override
	public JobOffer getjobOfferByTitle(String title, int entId) {
		Enterprise ent = em.find(Enterprise.class, entId);
		Query query = em.createQuery("select o from jobOffer o where o.enterprise =:e and "
				+ "o.title=:title");
		query.setParameter("e", ent);
		query.setParameter("title", title);
		try {
			return (JobOffer) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}


	

}
