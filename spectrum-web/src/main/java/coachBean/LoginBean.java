package coachBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import coach.RegisterRemote;
import entities.User;
@ManagedBean
@SessionScoped
public class LoginBean {
	private String login;
	private String password;
	private String email;
	private String picture;

	private User user;
	private boolean logged_In; //default is false
	
	@EJB
	RegisterRemote userService;
	
	public User getUser() {
		return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String do_Login(){
		String navigate_To = "null";
		user = userService.getUserByEmailAndPassword(login, password);
		
		if(user != null ){
			navigate_To = "/pages/Condidate?faces-redirect=true";
			setLogged_In(true);
		}else{
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad credentials"));
		}
		
		return navigate_To;
	}

	public String do_Logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/pages/Register?faces-redirect=true";
	}

	public boolean isLogged_In() {
		return logged_In;
	}

	public void setLogged_In(boolean logged_In) {
		this.logged_In = logged_In;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public User getEmploye() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RegisterRemote getUserService() {
		return userService;
	}

	public void setUserService(RegisterRemote userService) {
		this.userService = userService;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
