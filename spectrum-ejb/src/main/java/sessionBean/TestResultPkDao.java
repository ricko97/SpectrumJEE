package sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.TestResultPk;

/**
 * Session Bean implementation class TestResultPkDao
 */
@Stateless
@LocalBean
public class TestResultPkDao implements TestResultPkDaoRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TestResultPkDao() {
        
    }

	@Override
	public void addTestResultPk(TestResultPk e) {
		em.persist(e);
		
	}

	@Override
	public void updateTestResultPk(TestResultPk e) {
		em.merge(e);
	}

	@Override
	public TestResultPk findTestResultPk(int id) {
		
		return em.find(TestResultPk.class,id);
	}

	@Override
	public void deleteTestResultPk(TestResultPk e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<TestResultPk> addTestResultPk() {
		 
		return em.createQuery("select e from TestResultPk e ",TestResultPk.class).getResultList()  ;
	}
}
