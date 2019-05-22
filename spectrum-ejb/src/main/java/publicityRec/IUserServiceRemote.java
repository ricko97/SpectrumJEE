package publicityRec;

import java.util.List;

import javax.ejb.Remote;
import entities.User;


@Remote
public interface IUserServiceRemote {
	public User getUser(String email, String mdp);
	public List<User> findCompanies();
	public User findUserById(int id);

}
