package coachBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;
import coach.RegisterLocal;

@ManagedBean
@SessionScoped
public class sendToCoach implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private int id;
	private String address;
	private Date birth;
	private String Name;
	private String password;
	private String email;
	private String username;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private boolean logged_In;
	private Integer appreciation;

	private List<User> listemed = new ArrayList<>();
	private User userSelected = new User();
	private User user1 = new User();
	private User user = new User();
	@PersistenceContext
	EntityManager em;
	@EJB
	private RegisterLocal RegisterLocal;

	@PostConstruct
	public void init() {
		listemed = RegisterLocal.findAllUsers();
		user = RegisterLocal.findById(1);
	}

	public List<User> getListemed() {
		return listemed;
	}

	public void setListemed(List<User> listemed) {
		this.listemed = listemed;
	}

	/**
	 * 
	 */
	public void doSelect() {

	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	public  void  removeEmploye(int  id)
	{  RegisterLocal.deleteEmployeById(id);
	}
	
	
	
	
	
	public void doSupp1() {		
			user1 = RegisterLocal.findById(userSelected.getId());
			RegisterLocal.deleteUser(user1.getId());
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

	public Integer getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(Integer appreciation) {
		this.appreciation = appreciation;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public RegisterLocal getRegisterLocal() {
		return RegisterLocal;
	}

	public void setRegisterLocal(RegisterLocal registerLocal) {
		RegisterLocal = registerLocal;
	}

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

	public void addAppreciation() {
		RegisterLocal.createCoach(new User(Name));
	}
	
	public void addCandidate() {
		RegisterLocal.createCoach(new User(Name,username,email));
	}

	public void doAdd() {
		RegisterLocal.createCoach(user);
	}

	public void removeUser(int id) {
		RegisterLocal.deleteUser(id);
	}

	public void updateUser(User user) {
	//	id = user.getId();
		address = user.getAddress();
		birth = user.getBirth();
		Name = user.getName();

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/*
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
*/
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
