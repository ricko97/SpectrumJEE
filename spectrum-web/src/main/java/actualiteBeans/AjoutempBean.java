package actualiteBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import entities.Actualite;
import entities.Commentaire;
import entities.User;
import actualite.ServiceActualite;
import actualite.ServiceUser;


@ManagedBean(name = "ajoutembean")
@SessionScoped
public class AjoutempBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private String email;
	private String password;
	private Boolean isActif;
	private User employes;
	private String actualiteString;
	private String commentaireString;
	//@ManagedProperty(value = "#{loginBean}")
	//LoginBean loginBean;

	@EJB
	ServiceUser employeService;
	@EJB
	ServiceActualite actualiteservice;

	public void AjoutEmploye() {
		// if(loginBean==null || !loginBean.getLoggedIn()){
		// return"/login?faces-redirect=true";

		// }
		// employeService.ajouterEmploye(new Employe(login, password, email,
		// isActif, role));

		// return null;

	}
	public String ajouterCommentaire(Actualite actualite,String commentaire){
		Commentaire c = new Commentaire();
		c.setContent(commentaire);
		System.out.println("commentaire:"+commentaire);
     	actualiteservice.updateActualite(actualite,  c);
     	
		return "null";
	}
	public String ajouterActualite(){
		Actualite actualite = new Actualite();
		actualite.setContent(actualiteString);
		
		FacesContext context = FacesContext.getCurrentInstance();
		actualiteservice.addActualite(
		((User)context.getExternalContext().getSessionMap().get("user")), actualite);
	
		return "null";
	}

	public List<Actualite> getActualites() {
		return actualiteservice.getAllActualites();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}
	public String getActualiteString() {
		return actualiteString;
	}
	public void setActualiteString(String actualiteString) {
		this.actualiteString = actualiteString;
	}
	public String getCommentaireString() {
		return commentaireString;
	}
	public void setCommentaireString(String commentaireString) {
		this.commentaireString = commentaireString;
	}
	
	

}