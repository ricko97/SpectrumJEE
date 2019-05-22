package enterpriseServices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Payment;

/**
 * Session Bean implementation class PaymentDao
 */
@Stateless
@LocalBean
public class PaymentDao implements PaymentDaoRemote {

	@PersistenceContext(name="spectrum-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public PaymentDao() {
        
    }

	@Override
	public void addPayment(Payment e) {
		em.persist(e);
		
	}

	@Override
	public void updatePayment(Payment e) {
		em.merge(e);
	}

	@Override
	public Payment findPayment(int id) {
		
		return em.find(Payment.class,id);
	}

	@Override
	public void deletePayment(Payment e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Payment> addPayment() {
		 
		return em.createQuery("select e from Payment e ",Payment.class).getResultList()  ;
	}
}
