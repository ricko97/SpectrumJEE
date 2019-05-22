package sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Workshop;

/**
 * Session Bean implementation class WorkshopDao
 */
@Stateless
@LocalBean
public class WorkshopDao implements WorkshopDaoRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public WorkshopDao() {
        
    }

	@Override
	public void addWorkshop(Workshop e) {
		em.persist(e);
		
	}

	@Override
	public void updateWorkshop(Workshop e) {
		em.merge(e);
	}

	@Override
	public Workshop findWorkshop(int id) {
		
		return em.find(Workshop.class,id);
	}

	@Override
	public void deleteWorkshop(Workshop e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Workshop> addWorkshop() {
		 
		return em.createQuery("select e from Workshop e ",Workshop.class).getResultList()  ;
	}
}
