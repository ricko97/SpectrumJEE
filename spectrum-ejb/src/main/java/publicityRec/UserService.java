package publicityRec;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Reclamation;
import entities.Role;
import entities.Target;
import entities.User;

@Stateless
@LocalBean
public class UserService implements IUserServiceRemote {

@PersistenceContext(unitName = "spectrum-ejb")
EntityManager em;


@Override
public User getUser(String email, String password) {
	TypedQuery<User> query = 
	em.createQuery("select e from User e WHERE e.email=:email and e.password=:password ", User.class); 
	query.setParameter("email", email); 
	query.setParameter("password", password); 
	User employe = null; 
	try {
		employe = query.getSingleResult(); 
	}
	catch (Exception e) {
		System.out.println("Erreur : " + e);
	}
	return employe;
}
@Override
public User findUserById(int id) {
	User user = em.find(User.class, id);
	return user;
}

@Override
public List<User> findCompanies() {
	TypedQuery<User> query= em.createQuery(
			"select r from User r where r.role =:role", 
			User.class);
			query.setParameter("role", Role.enterpriseAdmin);
			return query.getResultList();
}

}
