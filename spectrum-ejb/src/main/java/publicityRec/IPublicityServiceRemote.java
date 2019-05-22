package publicityRec;

import java.util.List;

import javax.ejb.Remote;

import entities.Publicity;
import entities.Target;

@Remote
public interface IPublicityServiceRemote {
public int addPublicity(Publicity publicity);
public void removePublicity(int id);
public void updatePublicity(Publicity publicity);
public Publicity findPublicityById(int id);
public List<Publicity> findPublicityByOwner(int id);
public List<Publicity> findAllPublicities();
public List<Publicity> findPublicityByTarget(Target t);

}
