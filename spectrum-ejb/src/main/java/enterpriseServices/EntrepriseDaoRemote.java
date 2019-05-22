package enterpriseServices;
import entities.Enterprise;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EntrepriseDaoRemote {
	public void addEnterprise(Enterprise e);
	public void updateEnterprise(Enterprise e);
	public Enterprise findEnterprise(int id);
	public void deleteEnterprise(Enterprise e);
	public Enterprise findEnterpriseByUsername(String userName);
	public Enterprise findEnterpriseByEmail(String email);
	public List<Enterprise> findAllEnterprise();
	public Enterprise findEnterpriseByName(String Name);
	

}
