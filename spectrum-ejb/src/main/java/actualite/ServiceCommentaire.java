package actualite;

import javax.ejb.Remote;

import entities.Actualite;
import entities.Commentaire;
import entities.User;

@Remote
public interface ServiceCommentaire {
	public void addCommantaire(User u,Commentaire c,Actualite a);
    
}
