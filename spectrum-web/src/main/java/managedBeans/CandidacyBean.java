package managedBeans;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import entities.Candidacy;
import entities.CandidacyStatus;
import entities.Test_t;
import jobservices.CandidacyService;
import jobservices.InterviewService;

@ManagedBean
@SessionScoped
public class CandidacyBean {
	
	@EJB
	CandidacyService candidacyService;
	@EJB
	InterviewService interviewService;
	
	private String msg;
	private List<Test_t>testTypes;
	private Test_t testToSend;
	private Candidacy selectedCandidacy;
	private Integer enterpriseId=1;
	private List<Candidacy>candidacies;
	
	@PostConstruct
	public void init() {
		testTypes = new ArrayList<Test_t>();
		testTypes.add(Test_t.Linguistic);
		testTypes.add(Test_t.Psychotechnical);
		testTypes.add(Test_t.Technical);
		testTypes.add(Test_t.Visioconference);
		candidacies = new ArrayList<Candidacy>();
		candidacies = candidacyService.getCandidaciesByEnt(enterpriseId);
	}
	
	public String launchVC() {
		String link = interviewService.getEnterpriseById(enterpriseId).getSkypeVC();
		String msg = "Hello Mme, Mr "+selectedCandidacy.getCandidate().getUser().getName()+"\n\n"
				+ "You're invited to rejoin this interview.\n"
				+ "Click on the link below\n"
				+ link+"\n\n\n"
				+ "Spectrum Team.";
		return link;
		
	}
	
	
	public String sendTests() {
				String msg = "Hello Mme, Mr "+selectedCandidacy.getCandidate().getUser().getName()+"\n\n"
						+ "We want to evaluate your level, so you are invited to pass some quizzes online.\n"
						+ "Click on the link below\n\n\n"
						+ "Spectrum Team.";
				//candidacyService.addTestsToCand(selectedCandidacy.getCandidate().getId(),enterpriseId);
				interviewService.envoyerMail(selectedCandidacy.getCandidate().getUser().getEmail(), 
						"Online Quizzes", msg);
		return "candidacies";
		
	}
	
	
	public String processing() {
		Candidacy c = selectedCandidacy;
		c.setStatus(CandidacyStatus.processing);
		candidacyService.modifyCandidacy(c);
		return "candidacies";
	}
	public String accepted() {
		Candidacy c = selectedCandidacy;
		c.setStatus(CandidacyStatus.accepted);
		candidacyService.modifyCandidacy(c);
		return "candidacies";
	}
	public String rejected() {
		Candidacy c = selectedCandidacy;
		c.setStatus(CandidacyStatus.rejected);
		candidacyService.modifyCandidacy(c);
		return "candidacies";
	}

	public Candidacy getSelectedCandidacy() {
		return selectedCandidacy;
	}

	public void setSelectedCandidacy(Candidacy selectedCandidacy) {
		this.selectedCandidacy = selectedCandidacy;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public List<Candidacy> getCandidacies() {
		return candidacies;
	}

	public void setCandidacies(List<Candidacy> candidacies) {
		this.candidacies = candidacies;
	}

	public Test_t getTestToSend() {
		return testToSend;
	}

	public void setTestToSend(Test_t testToSend) {
		this.testToSend = testToSend;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Test_t> getTestTypes() {
		return testTypes;
	}

	public void setTestTypes(List<Test_t> testTypes) {
		this.testTypes = testTypes;
	}

}
