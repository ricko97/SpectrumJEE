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

import entities.Course;
import services.RegisterLocal;


@ManagedBean
@SessionScoped
public class CourseBean implements Serializable{
	





	private int id; 
	private Date date;
	private String desc; 





	private List<Course> listemed;
	private Course course =new Course();
	@PersistenceContext
	EntityManager em;
	@EJB
	private RegisterLocal RegisterLocal;
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	
	

public List<Course> getEmployes() {
	listemed = RegisterLocal.getAllCourse(); 
return listemed;
} 
	
	
	public void doAdd()
	{
		RegisterLocal.addCourse(course);
	}



	public void addC(){
		RegisterLocal.addCourse(new Course(id,date,desc));
	}

	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
	}





	public String getDesc() {
		return desc;
	}





	public void setDesc(String desc) {
		this.desc = desc;
	}





	public List<Course> getListemed() {
		return listemed;
	}







	public Course getCourse() {
		return course;
	}





	public void setCourse(Course course) {
		this.course = course;
	}



	public void setListemed(List<Course> listemed) {
		this.listemed = listemed;
	}
	
	
	
	
	
	
	
	
	
}
