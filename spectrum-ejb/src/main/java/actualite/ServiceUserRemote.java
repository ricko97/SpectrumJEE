package actualite;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;
@Stateless
@LocalBean
public class ServiceUserRemote implements ServiceUser{

	@PersistenceContext(name = "spectrum-ejb")
	EntityManager em;
	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User checkUser(String username, String password) {
		TypedQuery<User> query = em.createQuery("select u from User u where u.username=:username and u.password=:password",User.class);
		query.setParameter("password", password);
		query.setParameter("username", username);
		try{
			return query.getSingleResult() ;
		}catch(Exception e){
			return null;
		}
	}

}
