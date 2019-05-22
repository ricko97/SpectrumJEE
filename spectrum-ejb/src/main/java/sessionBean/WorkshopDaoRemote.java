package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entities.Workshop;

@Remote
public interface WorkshopDaoRemote {
	public void addWorkshop(Workshop e);
	public void updateWorkshop(Workshop e);
	public Workshop findWorkshop(int id);
	public void deleteWorkshop(Workshop e);
	public List<Workshop> addWorkshop();

}
