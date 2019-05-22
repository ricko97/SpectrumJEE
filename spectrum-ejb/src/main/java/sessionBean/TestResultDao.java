package sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.TestResult;

/**
 * Session Bean implementation class TestResultDao
 */
@Stateless
@LocalBean
public class TestResultDao implements TestResultDaoRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TestResultDao() {
        
    }

	@Override
	public void addTestResult(TestResult e) {
		em.persist(e);
		
	}

	@Override
	public void updateTestResult(TestResult e) {
		em.merge(e);
	}

	@Override
	public TestResult findTestResult(int id) {
		
		return em.find(TestResult.class,id);
	}

	@Override
	public void deleteTestResult(TestResult e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<TestResult> addTestResult() {
		 
		return em.createQuery("select e from TestResult e ",TestResult.class).getResultList()  ;
	}
}
