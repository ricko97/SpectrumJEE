package enterpriseServices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Interview;

/**
 * Session Bean implementation class InterviewDao
 */
@Stateless
@LocalBean
public class InterviewDao implements InterviewDaoRemote {

	@PersistenceContext(name="spectrum-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public InterviewDao() {
        
    }

	@Override
	public void addInterview(Interview e) {
		em.persist(e);
		
	}

	@Override
	public void updateInterview(Interview e) {
		em.merge(e);
	}

	@Override
	public Interview findInterview(int id) {
		
		return em.find(Interview.class,id);
	}

	@Override
	public void deleteInterview(Interview e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Interview> addInterview() {
		 
		return em.createQuery("select e from Interview e ",Interview.class).getResultList()  ;
	}
}
