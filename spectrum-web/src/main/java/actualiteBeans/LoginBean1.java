package actualiteBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import entities.User;
import actualite.ServiceUserRemote;

@ManagedBean(name = "loginBean1", eager = true)
@SessionScoped
public class LoginBean1 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String log;
	
	private User a;

	@EJB
	ServiceUserRemote employeService;

	public String doLogin() {
		String navigateTo = "null";
		a = employeService.checkUser(login, password);

		if (a != null) {

			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", a);
			navigateTo = "/pages/admin/welcome?faces-redirect=true";

		} else {

			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
		}
		System.out.println("bad credentials");
		return navigateTo;
	}

	public String doLogout() {
		String navigateTo = "login.jsf?face-redirect=true";
		return navigateTo;
		
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LoginBean1() {
		super();
	}

}