package coachBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GoTo implements Serializable{
	
	
	
	
	
	public String GotoProfile(){
		String navigateTo="pages/Coach?faces-redirect=true";
		System.out.println("noura");
	    return navigateTo;
	}
	
	
	
	
	
	
	
	public String GotoInterviewk(){
		String navigateTo="pages/addCourses?faces-redirect=true";
		System.out.println("nour");
	    return navigateTo;
	}
	
	public String GotoInterviewkk(){
		String navigateTo="SendCourseToCandidate?faces-redirect=true";
		System.out.println("nour");
	    return navigateTo;
	}
	
	public String GotoRecrute(){
		String navigateTo="template/ListGrudCandidate?faces-redirect=true";
		System.out.println("noura");
	    return navigateTo;
	}
	public String GotoAppreciationsDetails(){
		String navigateTo="template/sent_repports?faces-redirect=true";
		System.out.println("noura");
	    return navigateTo;
	}
	
	public String GotoMaillingJobyBook(){
		String navigateTo="MailingJobyBook?faces-redirect=true";
		System.out.println("noura");
	    return navigateTo;
	}
	public String GotoChhoseDatesInterview(){
		String navigateTo="JobseekerDateDispo?faces-redirect=true";
		System.out.println("noura");
	    return navigateTo;
	}
}
