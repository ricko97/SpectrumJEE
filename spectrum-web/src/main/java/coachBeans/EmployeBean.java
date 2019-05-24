package coachBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Course;
import coach.RegisterRemote;



@ManagedBean
@ViewScoped
public class EmployeBean {
private int id;
private String desc;
private Date date;

private static final long serialVersionUID = 1L;
private List<Course> courses;
private  Integer  employeIdToBeUpdated;







@EJB
RegisterRemote employeService;




public List<Course> getEmployes() {
courses = employeService.getAllCourse(); 
return courses;
} 
/*
public  void  removeEmploye(int  id_Employe)
{  employeService.deleteEmployeById(id_Employe);
}

public  void  modifier(Employe  employe)
{
	this.setEmail(employe.getEmail()); 
	this.setIs_Actif(employe.getIs_Actif());
	this.setLogin(employe.getLogin());
	this.setPassword(employe.getPassword()); 
	this.setNom(employe.getNom());
	this.setPrenom(employe.getPrenom());
	this.setRole(employe.getRole()); 
	this.setEmployeIdToBeUpdated(employe.getId_Employe());
}
*/
public Integer getEmployeIdToBeUpdated() {
	return employeIdToBeUpdated;
}




public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
	this.employeIdToBeUpdated = employeIdToBeUpdated;
}


public RegisterRemote getEmployeService() {
	return employeService;
}
public void setEmployeService(RegisterRemote employeService) {
	this.employeService = employeService;
}
public void setEmployes(List<Course> employes) {
	this.courses = employes;
}
public void addEmploye(){
	employeService.addCourse(new Course(id,date,desc));
}
/*
public void mettreAjourEmploye(){
	System.out.println("Test");
	Employe emp = new Employe();
	emp.setNom(nom);
	emp.setPrenom(prenom);
	emp.setEmail(email);
	emp.setPassword(password);
	emp.setRole(role);
	emp.setIs_Actif(is_Actif);
	emp.setLogin(login);
	emp.setId_Employe(employeIdToBeUpdated);
//employeService.updateEmploye(new Employe(nom,prenom,email,password,role,is_Actif,login,employeIdToBeUpdated));
	employeService.updateEmploye(emp);

	System.out.println("After Test");
}*/
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public List<Course> getCourses() {
	return courses;
}
public void setCourses(List<Course> courses) {
	this.courses = courses;
}

}
