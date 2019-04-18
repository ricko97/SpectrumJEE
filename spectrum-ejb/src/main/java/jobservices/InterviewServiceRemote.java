package jobservices;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Interview;
import entities.Question;
import entities.Test;
import entities.TestResult;
import entities.TestResultPk;
import entities.Test_t;

@Remote
public interface InterviewServiceRemote {

	public int addQuestionToTest(int testId, Question question);
	public void modifyQuestion(Question question);
	public void removeQuestion(Question question);
	public Question searchQuestion(int testId, String content);
	public Test addTest(Test test, int enterpriseId);
	public Test modifyTest(Test test);
	public void deleteTest(Test test);
	public List<Test> getAllTest(int enterpriseId);
	public Test searchTest(int enterpriseId, Test_t testType);
	public List<Question>getQuestionsFromTest(int testId);
	public void addTestResult(TestResultPk testResultPk, TestResult testResult);
	public TestResultPk getTestResultPk(int candidateId, int testId);
	public Interview plannifyInterview(TestResult testResult, Date date);
	public Interview plannifyInterviewAuto(TestResult testResult);
	public Interview searchInterview(TestResultPk testResultPk);
	public List<Interview> getAllInterviews();
	public void modifyInterview(Interview interview);
	public void cancelInterview(int interviewId);
	public TestResult getTestResult(TestResultPk testResultPk);
	public Calendar getHighestDay();
	
	
}
