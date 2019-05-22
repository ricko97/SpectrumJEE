package sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.JobOffer;

/**
 * Session Bean implementation class JobOfferDao
 */
@Stateless
@LocalBean
public class JobOfferDao implements JobOfferDaoRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public JobOfferDao() {
        
    }

	@Override
	public void addJobOffer(JobOffer e) {
		em.persist(e);
		
	}

	@Override
	public void updateJobOffer(JobOffer e) {
		em.merge(e);
	}

	@Override
	public JobOffer findJobOffer(int id) {
		
		return em.find(JobOffer.class,id);
	}

	@Override
	public void deleteJobOffer(JobOffer e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<JobOffer> addJobOffer() {
		 
		return em.createQuery("select e from JobOffer e ",JobOffer.class).getResultList()  ;
	}
}
