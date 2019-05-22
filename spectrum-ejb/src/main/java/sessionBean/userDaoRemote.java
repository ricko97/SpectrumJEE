package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface userDaoRemote {
	public void addUser(User e);
	public void updateUser(User e);
	public User findUser(int id);
	public void deleteUser(int id);
	public User findUserByUserName(String string);
	public User findUserByEmail(String string);
	public List<User> findAlluser();
	List<User> findAllUserByEnterpriseName(String enterpriseName);
	

}
