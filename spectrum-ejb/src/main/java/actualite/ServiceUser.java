package actualite;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface ServiceUser {
	public void addUser(User u);
	public void updateUser(User u);
	public void deleteUser(User u);
	public User checkUser(String login,String password);
}
