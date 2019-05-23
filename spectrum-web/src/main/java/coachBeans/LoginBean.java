package coachBeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import coach.RegisterRemote;
import enterpriseBeans.BCrypt;
import enterpriseBeans.Enterprisebean;
import enterpriseServices.userDao;
import entities.Role;
import entities.User;
@ManagedBean
@SessionScoped
public class LoginBean {
	private String login;
	private String password;
	private String email;
	private String picture;

	private User currentUser;
	private User currentEnterprise;
	private User currentCoach;
	private User currentCandidate;
	private boolean logged_In; //default is false
	
	@EJB
	RegisterRemote userService;
	@EJB
	userDao dao;
	@ManagedProperty("#{enterpriseBean}")
	private Enterprisebean enterpriseBean;
	
	
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
		String navigate_To = null;
		currentUser = dao.findUserByUserName(login);
		if(currentUser.getUsername().equals(login) && BCrypt.checkpw(password,currentUser.getPassword())){
			if (currentUser.getRole()==Role.candidate)
				navigate_To = "candidate/home?faces-redirect=true";
			else if(currentUser.getRole()==Role.coach)
				navigate_To = "coach/home?faces-redirect=true";
			else if(!currentUser.getEnterpriseName().isEmpty()) {
				navigate_To =enterpriseBean.singIn2(currentUser);
			}
			else
				navigate_To = "admin/home?faces-redirect=true";
			setLogged_In(true);
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad credentials"));
		}
		
		return navigate_To;
	}

	public String do_Logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "Register?faces-redirect=true";
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

	public RegisterRemote getUserService() {
		return userService;
	}

	public void setUserService(RegisterRemote userService) {
		this.userService = userService;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Enterprisebean getEnterpriseBean() {
		return enterpriseBean;
	}

	public void setEnterpriseBean(Enterprisebean enterpriseBean) {
		this.enterpriseBean = enterpriseBean;
	}
}
