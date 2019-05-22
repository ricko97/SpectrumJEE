package sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Question;

/**
 * Session Bean implementation class QuestionDao
 */
@Stateless
@LocalBean
public class QuestionDao implements QuestionDaoRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public QuestionDao() {
        
    }

	@Override
	public void addQuestion(Question e) {
		em.persist(e);
		
	}

	@Override
	public void updateQuestion(Question e) {
		em.merge(e);
	}

	@Override
	public Question findQuestion(int id) {
		
		return em.find(Question.class,id);
	}

	@Override
	public void deleteQuestion(Question e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Question> addQuestion() {
		 
		return em.createQuery("select e from Question e ",Question.class).getResultList()  ;
	}
}
