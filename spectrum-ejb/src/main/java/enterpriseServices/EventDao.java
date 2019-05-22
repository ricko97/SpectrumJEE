package enterpriseServices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Event;

/**
 * Session Bean implementation class EventDao
 */
@Stateless
@LocalBean
public class EventDao implements EventDaoRemote {

	@PersistenceContext(name="spectrum-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public EventDao() {
        
    }

	@Override
	public void addEvent(Event e) {
		em.persist(e);
		
		
	}

	@Override
	public void updateEvent(Event e) {
		em.merge(e);
	}

	@Override
	public Event findEvent(int id) {
		
		return em.find(Event.class,id);
	}

	@Override
	public void deleteEvent(Event e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Event> addEvent() {
		 
		return em.createQuery("select e from Event e ",Event.class).getResultList()  ;
	}
}
