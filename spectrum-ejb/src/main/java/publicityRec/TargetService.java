package publicityRec;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Target;

@Stateless
@LocalBean
public class TargetService implements ITargetServiceRemote {

@PersistenceContext(unitName = "spectrum-ejb")
EntityManager em;


@Override
public int addTarget(Target target) {
	em.persist(target);
	return target.getId();
}
@Override
public void removeTarget(int id) {
	em.remove(em.find(Target.class, id));
	
}
@Override
public void updateTarget(Target target) {
	Target pub = em.find(Target.class, target.getId());
	pub.setName(target.getName());
	
}
@Override
public Target findTargetById(int id) {
	Target target = em.find(Target.class, id);
	return target;
}
@Override
public List<Target> findAllTargets() {
	List<Target> publicities = em.createQuery("from Target", Target.class).getResultList();
	return publicities;
}
}
