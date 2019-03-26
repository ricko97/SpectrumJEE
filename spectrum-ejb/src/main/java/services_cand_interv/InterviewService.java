package services_cand_interv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Enterprise;
import entities.Interview;
import entities.Question;
import entities.Test;
import entities.TestResult;
import entities.TestResultPk;
import entities.Test_t;

@Stateless
public class InterviewService implements InterviewServiceRemote {

	@PersistenceContext(unitName="spectrum-ejb")
	EntityManager em;
	
	@Override
	public int addQuestionToTest(int testId, Question question) {
		if (!this.searchQuestion(testId, question.getContent())) {
			question.setTest(em.find(Test.class, testId));
			em.persist(question);
			return question.getId();
		}else
			return 0;
	}

	@Override
	public void modifyQuestion(int questionId) {
		Question question = em.find(Question.class, questionId);
		em.merge(question);
	}

	@Override
	public void removeQuestion(int questionId) {
		Question question = em.find(Question.class, questionId);
		em.remove(question);
	}
	@Override
	public boolean searchQuestion(int testId, String content) {
		for (Question question : em.find(Test.class,testId).getQuestions()) {
			if (question.getContent().equals(content))
					return true; 
		}
		return false;
	}

	@Override
	public int addTest(Test test, int enterpriseId, List<Question> questions) {
		if(!this.searchTest(enterpriseId, test.getType())) {
			for (Question question : questions) {
				question.setTest(test);
			}
			test.setModified_at(new Date());
			test.setEnterprise(em.find(Enterprise.class, enterpriseId));
			em.persist(test);
			return test.getId();
		}else
			return 0;
	}

	@Override
	public void modifyTest(Test test) {
		em.merge(test);
	}

	@Override
	public void deleteTest(Test test) {
		em.remove(test);
	}
	
	@Override
	public boolean searchTest(int enterpriseId, Test_t testType) {
		for (Test test : em.find(Enterprise.class, enterpriseId).getTests()) {
			if (test.getType()==testType)
					return true; 
		}
		return false;
	}

	@Override
	public List<Question> getQuestionsFromTest(int testId) {
		return  em.find(Test.class, testId).getQuestions();
	}

	@Override
	public void addTestResult(TestResultPk testResultPk, TestResult testResult) {
		testResult.setTestResultPk(testResultPk);
		if (testResult.getScore()>=0.5)
			testResult.setPassed(true);
		else
			testResult.setPassed(false);
		
		em.persist(testResult);
	}

	@Override
	public TestResultPk getTestResultPk(int candidateId, int testId) {
		TestResultPk testResultPk = new TestResultPk();
		testResultPk.setCandidateId(candidateId);
		testResultPk.setTestId(testId);
		return testResultPk;
	}

	@Override
	public Interview plannifyInterview(TestResult testResult, Date date) {
		if (!this.searchInterview(testResult.getTestResultPk())) {
			Interview interview = new Interview();
			interview.setTestResult(testResult);
			interview.setDate(date);
			em.persist(interview);
			return interview;
		}else
			return null;

	}

	@Override
	public Interview plannifyInterviewAuto(TestResult testResult) {
		if(testResult.getPassed()) {
			if (!this.searchInterview(testResult.getTestResultPk())) {
				Calendar cal = Calendar.getInstance();
				if (this.isLastMonthDay(this.getHighestDay()) && this.availableHours().isEmpty()) {
					cal.set(getHighestDay().get(Calendar.YEAR), getHighestDay().get(Calendar.MONTH)+1, 1,9,0);
					System.out.println("1");
				}else if(this.isLastMonthDay(this.getHighestDay())) {
					cal.set(getHighestDay().get(Calendar.YEAR), getHighestDay().get(Calendar.MONTH)+1, 1,
							this.availableHours().get(0),0);
					System.out.println("2");
				}else if(this.isWeekendDay(getHighestDay())) {
					cal.setWeekDate(getHighestDay().get(Calendar.YEAR), getHighestDay().get(Calendar.WEEK_OF_YEAR)+1,1);
					System.out.println("3");
				}else if(this.availableHours().isEmpty()){
					cal.set(getHighestDay().get(Calendar.YEAR), getHighestDay().get(Calendar.MONTH),
							getHighestDay().get(Calendar.DAY_OF_MONTH)+1,9,0);
					System.out.println("4");
				}else {
					cal.set(getHighestDay().get(Calendar.YEAR), getHighestDay().get(Calendar.MONTH),
							getHighestDay().get(Calendar.DAY_OF_MONTH),this.availableHours().get(0),0);
					System.out.println("5");
				}
				Interview interview = new Interview();
				interview.setDate(cal.getTime());
				interview.setTestResult(testResult);
				em.persist(interview);
				return interview;
			}else
				return null;
		}else
			return null;
	}
	
	@Override
	public boolean isLastMonthDay(Calendar cal) {
		if ((cal.get(Calendar.DAY_OF_MONTH)==28 && cal.get(Calendar.MONTH)==1)||
				(cal.get(Calendar.DAY_OF_MONTH)==31 && cal.get(Calendar.MONTH)!=1)){
			return true;
		}
		return false;	
	}
	
	public boolean isWeekendDay(Calendar cal) {
		if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY||
				cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {
			return true;
		}
		return false;
		
	}

	@Override
	public boolean searchInterview(TestResultPk testResultPk) {
		for (Interview interview : em.createQuery("select i from interview i",Interview.class).
				getResultList()) {
			if(interview.getTestResult().getTestResultPk().equals(testResultPk)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Integer>availableHours() {
		List<Integer>hoursAv = new ArrayList<Integer>();
		hoursAv.add(9);hoursAv.add(10);hoursAv.add(11);hoursAv.add(14);hoursAv.add(15);
		hoursAv.add(16);hoursAv.add(17);
		List<Date>interviewDates = em.createQuery("select i.date from interview i").getResultList();
		if (!interviewDates.isEmpty()) {
			for (Date date : interviewDates) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				if ((cal.get(Calendar.DAY_OF_MONTH)==getHighestDay().get(Calendar.DAY_OF_MONTH))&&
						(cal.get(Calendar.MONTH)==getHighestDay().get(Calendar.MONTH))&&
						(cal.get(Calendar.YEAR)==getHighestDay().get(Calendar.YEAR))){
					if (hoursAv.contains(cal.get(Calendar.HOUR_OF_DAY))) {
						hoursAv.remove(hoursAv.indexOf(cal.get(Calendar.HOUR_OF_DAY)));
					}
				}
			}
		}
		return hoursAv;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Calendar getHighestDay() {
		List<Date>interviewDates = em.createQuery("select i.date from interview i").getResultList();
		Calendar calendar = Calendar.getInstance();
		Calendar highest = Calendar.getInstance();
		highest.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 
				calendar.get(Calendar.DAY_OF_MONTH)+1);
		if (!interviewDates.isEmpty()) {
			Collections.sort(interviewDates, new Comparator<Date>() {
				@Override
				public int compare(Date o1, Date o2) {
					// TODO Auto-generated method stub
					return 0;
				}
			});
			highest.setTime(interviewDates.get(interviewDates.size()-1));
		}
		
		return highest;
	}

	@Override
	public TestResult getTestResult(TestResultPk testResultPk) {
		return em.find(TestResult.class, testResultPk);
	}


}
