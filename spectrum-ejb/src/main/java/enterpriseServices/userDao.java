package enterpriseServices;




import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import entities.Candidate;
import entities.Coach;
import entities.Enterprise;
import entities.User;

/**
 * Session Bean implementation class userDao
 */
@Stateless
@LocalBean
public class userDao implements userDaoRemote {
	@PersistenceContext(name="spectrum-ejb")
	EntityManager em;
/**
 * Default constructor. 
 */
public userDao() {
    
}

@Override
public void addUser(User e) {
	em.persist(e);
	
	
}

@Override
public void updateUser(User e) {
	em.merge(e);
}

@Override
public User findUser(int id) {
	
	return em.find(User.class,id);
}
@Override
public User findUserByUserName(String userName) {
	
	User u=new User();
	u.setUsername("g");
	          try {
	         u=em.createQuery(
			  "SELECT u from user u WHERE u.username = :username", User.class).
			   setParameter("username", userName).getSingleResult();
	          }catch (NoResultException e) {
	        	  
	          }
	          
      return u;
	}
@Override
public List<User> findAllUserByEnterpriseName(String enterpriseName) {
	
	
	         return em.createQuery(
			  "SELECT u from user u WHERE u.enterpriseName = :enterpriseName", User.class).
			   setParameter("enterpriseName", enterpriseName).getResultList();
	          
	          
      
	}
@Override
public User findUserByEmail(String email) {
	User u=new User();
	u.setEmail("mail@gmail.com");
	          try {
	         u=em.createQuery(
			  "SELECT u from user u WHERE u.email = :email", User.class).
			   setParameter("email", email).getSingleResult();
	          }catch (NoResultException e) {
	        	  
	          }
	          
      return u;
}


@Override
public void deleteUser(int id) {
	//em.remove(e);
	em.remove(em.merge(em.find(User.class, id)));
}

@Override
public List<User> findAlluser() {
	 
	return em.createQuery("select e from user e",User.class).getResultList()  ;
}

@Override
public void addCandidate(Candidate candidate) {
	em.persist(candidate);
	
}

@Override
public void addCoach(Coach coach) {
	em.persist(coach);
	
}

@Override
public void addEnterprise(Enterprise enterprise) {
	em.persist(enterprise);
	
}
}
