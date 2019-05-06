package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;
import services.RegisterLocal;


@ManagedBean
@SessionScoped
public class sendToCoach implements Serializable{
	private int id; 
	private String address; 
	private Date birth;
	private String Name; 
	private String password; 
	private boolean logged_In;
	private Integer appreciation;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	public boolean isLogged_In() {
		return logged_In;
	}




	public void setLogged_In(boolean logged_In) {
		this.logged_In = logged_In;
	}

	private List<User> listemed;
	private User user =new User();
	@PersistenceContext
	EntityManager em;
	@EJB
	private RegisterLocal RegisterLocal;
	
	
	
	
	
	public List<User> getListemed() {
		return listemed;
	}

	public void setListemed(List<User> listemed) {
		this.listemed = listemed;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	
	public void addAppreciation()
	{   
		RegisterLocal.createCoach(new User(Name)); 
	}
	public void doAdd()
	{
		RegisterLocal.createCoach(user);
	}
	
	public void removeUser(int id) {
		RegisterLocal.deleteUser(id);
	}
	
	
	public void updateUser(User user){
		 id=user.getId();
		 address=user.getAddress();
		 birth=user.getBirth();
		 Name=user.getName();
		 
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	
	
	
	
	
	
}
