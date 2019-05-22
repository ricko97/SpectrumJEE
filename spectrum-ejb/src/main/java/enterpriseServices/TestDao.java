package enterpriseServices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Test;

/**
 * Session Bean implementation class TestDao
 */
@Stateless
@LocalBean
public class TestDao implements TestDaoRemote {

	@PersistenceContext(name="spectrum-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TestDao() {
        
    }

	@Override
	public void addTest(Test e) {
		em.persist(e);
		
	}

	@Override
	public void updateTest(Test e) {
		em.merge(e);
	}

	@Override
	public Test findTest(int id) {
		
		return em.find(Test.class,id);
	}

	@Override
	public void deleteTest(Test e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Test> addTest() {
		 
		return em.createQuery("select e from Test e ",Test.class).getResultList()  ;
	}
}
