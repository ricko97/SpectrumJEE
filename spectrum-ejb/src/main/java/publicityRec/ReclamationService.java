package publicityRec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Publicity;
import entities.Reclamation;

@Stateless
@LocalBean
public class ReclamationService implements IReclamationServiceRemote, IReclamationServiceLocal {

@PersistenceContext(unitName = "spectrum-ejb")
EntityManager em;


@Override
public int addReclamation(Reclamation reclamation) {
	em.persist(reclamation);
	return reclamation.getId();
}
@Override
public void removeReclamation(int id) {
	em.remove(em.find(Reclamation.class, id));
	
}
@Override
public Reclamation findReclamationById(int id) {
	Reclamation reclamation = em.find(Reclamation.class, id);
	return reclamation;
}
@Override
public List<Reclamation> findAllReclamations() {
	List<Reclamation> publicities = em.createQuery("from Reclamation", Reclamation.class).getResultList();
	return publicities;
}
@Override
public List<Reclamation> findReclamationByCompany(int id) {
	List<Reclamation> publicities = new ArrayList<Reclamation>();
	publicities = em.createQuery("from Reclamation", Reclamation.class).getResultList();
	return publicities;
}
}
