package actualite;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Actualite;
import entities.Commentaire;
import entities.User;


@Stateless
@LocalBean


public class ServiceCommentaireRemote implements ServiceCommentaire{

	@PersistenceContext(unitName = "spectrum-ejb")
	EntityManager em;
	
	@Override
	public void addCommantaire(User u, Commentaire c, Actualite a) {
		
		
	}

}
