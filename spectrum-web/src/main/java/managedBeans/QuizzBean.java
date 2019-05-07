package managedBeans;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entities.Question;
import entities.Test;
import entities.TestResult;
import entities.TestResultPk;
import entities.Test_t;
import entities.User;
import jobservices.CandidacyService;
import jobservices.InterviewService;
import jobservices.VerifyRecaptcha;

@ManagedBean
@SessionScoped
public class QuizzBean{
	

	@EJB
	InterviewService interviewService;
	@EJB
	VerifyRecaptcha verifyRecaptcha;
	@EJB
	CandidacyService candidacyService;
	
	private Test test;
	private Integer enterpriseId = 1;
	private Integer candidateId;
	private String email;
	private Test_t testType;
	private Integer min,minutes;
	private Integer sec;
	private String answer;    
    private List<Question> questions;
    private List<String> choices;
    private Integer questionIndex = 0;
    private Question question;
    private Integer totalQuestion;
    private List<String> userAnswers;

     
    @PostConstruct
    public void init() {
    	questions = interviewService.getQuestionsFromTest(1);
    	totalQuestion = questions.size();
    	question = questions.get(questionIndex);
    	choices = new ArrayList<String>();
        choices.add(question.getAnswer());
        choices.add(question.getChoice3());
        choices.add(question.getChoice1());
        choices.add(question.getChoice2());
        testType = Test_t.Technical;
        userAnswers = new ArrayList<String>();
        test = interviewService.searchTest(enterpriseId, testType);
    	min = 0;//test.getDuration()-1;
    	minutes = 0;//test.getDuration()-1;
    	sec = 59;
    	System.out.println(totalQuestion+"*****************"+questions.size());
    }
    
    public String nextQuestion() {
    	userAnswers.add(answer);
    	questionIndex++;
    	question = questions.get(questionIndex);
    	choices.clear();
    	choices.add(question.getAnswer());
        choices.add(question.getChoice3());
        choices.add(question.getChoice1());
        choices.add(question.getChoice2());
        Collections.sort(choices, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});
		return null;
    }
    public String finishQuizz() {
    	userAnswers.add(answer);
    	questionIndex++;
    	String navigateTo = "finish";
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	TestResultPk pk = new TestResultPk();
    	TestResult result = new TestResult();
    	pk.setTestId(test.getId());
    	pk.setCandidateId(candidateId);
    	result.setTestResultPk(pk);
    	DecimalFormat df = new DecimalFormat("0.00");
    	result.setScore(Float.parseFloat(df.format(calculScore())));
    	interviewService.addTestResult(pk, result);
    	Date dateInt = interviewService.plannifyInterviewAuto(result).getDate();
    	if (result.getScore()>=0.5) {
    		interviewService.envoyerMail(result.getCandidate().getUser().getEmail(), 
    				result.getTest().getEnterprise().getUser().getName()+" Interview Scheduled", 
    				"Hello Mme, Mr "+result.getCandidate().getUser().getName()+" \n\n"
    						+ "You sucessfully passed the "+result.getTest().getType().toString()+" test, so "
    								+ "an interview has been scheduled for "+dateInt.toString()+".\n\n"
    										+ "Cordially.\nSpectrum Team.");
    	}
    	return navigateTo;
    }
    
    public float calculScore() {
    	float score=0;
    	for (int i=0;i<userAnswers.size();i++) {
			if (questions.get(i).getAnswer().equals(userAnswers.get(i)))
				score++;
		}
		return score/questions.size();
    	
    }
    public void decrementSec() {
		if (sec>0) {
			sec--;
		}else if(sec==0 && min>=0)
			sec = 59;
    }
	public String decrementMin() throws IOException {
		if(sec==0 && min >=0) {
			if(min==0) {
				min--;
				minutes=0;
			}else {
				min--;
				minutes--;
			}
		}
		else if(min==-1 && sec==0) {
			if(questions.size()-(questionIndex)==1)
				finishQuizz();
			else {
				for (int i=0;i<questions.size()-(questionIndex+1);i++) {
					userAnswers.add(null);
				}
				finishQuizz();
			}
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
			handleNavigation(FacesContext.getCurrentInstance(), null, "finish");
		}
		return null;
    }
	public boolean sec() {
		if (sec<10)
			return true;
		else
			return false;
	}
	public boolean aMinuteLeft() {
		if(minutes==0)
			return true;
		else
			return false;
	}
	
	public String submit_form(){
        try {
       String gRecaptchaResponse = FacesContext.getCurrentInstance().
       getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
       boolean verify = verifyRecaptcha.verify(gRecaptchaResponse);
       if(verify){
            return "Success";
       }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage( "Incorrect Captcha") );
            return null;
         }
        } catch (Exception e) {
            System.out.println(e);
        }
       return null;
    }
	
	public String startQuizz() {
		if (("Success").equals(submit_form())) {
			User user = candidacyService.getCandidateByEmail(email);
			if (user==null) {
				FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage( "Email not found") );
			}else {
				candidateId = user.getCandidate().getId();
				TestResultPk t = new TestResultPk();
				t.setCandidateId(candidateId);
				t.setTestId(test.getId());
				if (interviewService.getTestResult(t)!=null) {
					FacesContext context = FacesContext.getCurrentInstance();
		            context.addMessage(null, new FacesMessage( "Quizz already completed") );
				}else
					return "quizz?faces-redirect=true";
			}
		}
		return null;
		
	}

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(Integer questionIndex) {
		this.questionIndex = questionIndex;
	}

	public Test getTest() {
		return test;
	}

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Test_t getTestType() {
		return testType;
	}

	public void setTestType(Test_t testType) {
		this.testType = testType;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSec() {
		return sec;
	}

	public void setSec(Integer sec) {
		this.sec = sec;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public List<String> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<String> userAnswers) {
		this.userAnswers = userAnswers;
	}

}
