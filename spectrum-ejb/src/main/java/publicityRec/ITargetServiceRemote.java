package publicityRec;

import java.util.List;

import javax.ejb.Remote;

import entities.Target;

@Remote
public interface ITargetServiceRemote {
public int addTarget(Target target);
public void removeTarget(int id);
public Target findTargetById(int id);
public void updateTarget(Target target);
public List<Target> findAllTargets();

}
