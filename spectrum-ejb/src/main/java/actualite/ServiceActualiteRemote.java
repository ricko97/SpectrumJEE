package actualite;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Actualite;
import entities.Commentaire;
import entities.User;


@Stateless
@LocalBean
public class ServiceActualiteRemote implements ServiceActualite {

	@PersistenceContext(unitName = "spectrum-ejb")
	EntityManager em;
	@Override
	public void addActualite(User u, Actualite a) {
	    u=em.find(User.class, u.getId());
		a.setOwner(u);
		em.persist(a);
	}

	@Override
	public List<Actualite> getAllActualites() {
		return em.createQuery("select act from Actualite act ",Actualite.class).getResultList();
	}

	@Override
	public void updateActualite(Actualite a, Commentaire c) {
		a =em.find(Actualite.class,a.getId());
		c.setActualite(a);
		 em.persist(c);
		a.getCommentaires().add(c);
	    em.merge(a);
		
	}

}
