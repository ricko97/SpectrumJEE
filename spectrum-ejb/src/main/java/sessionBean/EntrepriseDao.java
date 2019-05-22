package sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import entities.Enterprise;
import entities.User;

/**
 *<?xml version="1.0" encoding="UTF-8"?>
<?import com.jfoenix.controls.JFXTreeCell?><JFXTreeCell/>
 Session Bean implementation class EntrepriseDao
 */
@Stateless
@LocalBean
public class EntrepriseDao implements EntrepriseDaoRemote {
     
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public EntrepriseDao() {
        
    }

	@Override
	public void addEnterprise(Enterprise e) {
		try {
		em.persist(e);
		//em.getTransaction().commit();
		}
		catch(Exception e3)
		{
			System.out.print("eeeeeeeeee");
		}
		
	}

	@Override
	public void updateEnterprise(Enterprise e) {
		em.merge(e);
	}


	@Override
	public Enterprise findEnterprise(int id) {
		
		return em.find(Enterprise.class,id);
	}
	@Override
	public Enterprise findEnterpriseByUsername(String userName) {
		Enterprise en=new Enterprise();
		User u=new User();u.setUsername("h");en.setUser(u);
		try {
		 en=em.createQuery(
				 "select e from enterprise e join e.user u WHERE u.username = :username ",Enterprise.class)
				 .setParameter("username", userName).getSingleResult();
		}catch (NoResultException e) {
			
		}
	      return en;
	     
	}
	@Override
	public Enterprise findEnterpriseByEmail(String email) {
		Enterprise en=new Enterprise();
		User u=new User();u.setEmail("@mail");en.setUser(u);
		try {
		 en=em.createQuery(
				 "select e from enterprise e join e.user u WHERE u.email = :email ",Enterprise.class)
				 .setParameter("email", email).getSingleResult();
		}catch (NoResultException e) {
			
		}
	      return en;
	     
	}
	@Override
	public Enterprise findEnterpriseByName(String Name) {
		
		Enterprise e=new Enterprise();
		e.setName("g");
		          try {
		         e=em.createQuery(
				  "SELECT u from enterprise u WHERE u.name = :name", Enterprise.class).
				   setParameter("name", Name).getSingleResult();
		          }catch (NoResultException e1) {
		        	  
		          }
		          
	      return e;
	}
		
	@Override
	public void deleteEnterprise(Enterprise e) {
		em.remove(em.merge(e));
		
	}

	@Override
	public List<Enterprise> findAllEnterprise() {
		 
		return em.createQuery("select e from Enterprise e ",Enterprise.class).getResultList()  ;
	}

}
