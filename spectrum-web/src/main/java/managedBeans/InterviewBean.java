package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Interview;
import jobservices.InterviewService;

@ManagedBean
@ViewScoped
public class InterviewBean {
	
	private Interview selectedInterview;
	private Integer enterpriseId=1;
	private List<Interview>interviews;
	
	@EJB
	InterviewService InterviewService;
	
	@PostConstruct
	public void init() {
		interviews = InterviewService.getInterviewsByEnt(enterpriseId);
	}
	
	public String cancel() {
		InterviewService.cancelInterview(selectedInterview.getId());
		return "interviews";
		
	}

	public Interview getSelectedInterview() {
		return selectedInterview;
	}


	public void setSelectedInterview(Interview selectedInterview) {
		this.selectedInterview = selectedInterview;
	}


	public Integer getEnterpriseId() {
		return enterpriseId;
	}


	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	public List<Interview> getInterviews() {
		return interviews;
	}


	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

}
