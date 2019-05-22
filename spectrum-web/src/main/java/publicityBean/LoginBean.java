package publicityBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import entities.Publicity;
import entities.Reclamation;
import entities.Target;
import entities.User;
import publicityRec.PublicityService;
import publicityRec.ReclamationService;
import publicityRec.TargetService;
import publicityRec.UserService;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable
{ private static final long serialVersionUID = 1L; 

private String login;
private String password;
private User user;
private boolean loggedIn;


@EJB
UserService userService = new UserService();

public String doLogin(){
	String navigateTo="null";
	user = userService.getUser(login, password);
    if (user !=null){
    	loggedIn=true;
	if (user.getRole().equals("ROLE_ADMIN")){
		navigateTo="/targets?faces-redirect=true";
	}
	if (user.getRole().equals("ROLE_ENTREPRISE")){
		navigateTo="/mesStats?faces-redirect=true";
	}
	if (user.getRole().equals("ROLE_CLIENT")){
		navigateTo="/home?faces-redirect=true";
	}
    }
	else {
		FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("bad credentials"));
	}
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

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public boolean isLoggedIn() {
	return loggedIn;
}

public void setLoggedIn(boolean loggedIn) {
	this.loggedIn = loggedIn;
}

}

