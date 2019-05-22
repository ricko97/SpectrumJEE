package actualite;

import java.util.List;

import javax.ejb.Remote;

import entities.Actualite;
import entities.Commentaire;
import entities.User;

@Remote
public interface ServiceActualite {
	public void addActualite(User u,Actualite a);
	public void updateActualite(Actualite a, Commentaire c);
	public List<Actualite> getAllActualites();
}
