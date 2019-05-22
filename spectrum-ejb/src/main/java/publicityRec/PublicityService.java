package publicityRec;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Publicity;
import entities.Target;
import entities.User;
//import timesheet.entities.Employe;

@Stateless
@LocalBean
public class PublicityService implements IPublicityServiceRemote {

@PersistenceContext(unitName = "spectrum-ejb")
EntityManager em;


@Override
public int addPublicity(Publicity publicity) {
	em.persist(publicity);
	return publicity.getId();
}
@Override
public void removePublicity(int id) {
	em.remove(em.find(Publicity.class, id));
	
}
@Override
public void updatePublicity(Publicity publicity) {
	Publicity pub = em.find(Publicity.class, publicity.getId());
	pub.setDescription(publicity.getDescription());
	pub.setName(publicity.getName());
	pub.setPhoto(publicity.getPhoto());
	
}
@Override
public Publicity findPublicityById(int id) {
	Publicity publicity = em.find(Publicity.class, id);
	return publicity;
}
@Override
public List<Publicity> findAllPublicities() {
	List<Publicity> publicities = em.createQuery("from Publicity", Publicity.class).getResultList();
	return publicities;
	
}
@Override
public List<Publicity> findPublicityByOwner(int id) {
	List<Publicity> publicities = em.createQuery("from Publicity", Publicity.class).getResultList();
	return publicities;
}
public List<Publicity> findPublicityByTarget(Target e) {
	List<Publicity> publicities = em.createQuery("from Publicity", Publicity.class).getResultList();
	return publicities;
}
}
