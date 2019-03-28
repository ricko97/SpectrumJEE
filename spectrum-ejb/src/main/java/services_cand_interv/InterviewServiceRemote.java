package services_cand_interv;

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
	public void modifyQuestion(int questionId);
	public void removeQuestion(int questionId);
	public boolean searchQuestion(int testId, String content);
	public int addTest(Test test, int enterpriseId, List<Question>questions);
	public void modifyTest(Test test);
	public void deleteTest(Test test);
	public boolean searchTest(int enterpriseId, Test_t testType);
	public List<Question>getQuestionsFromTest(int testId);
	public void addTestResult(TestResultPk testResultPk, TestResult testResult);
	public TestResultPk getTestResultPk(int candidateId, int testId);
	public Interview plannifyInterview(TestResult testResult, Date date);
	public Interview plannifyInterviewAuto(TestResult testResult);
	public Interview searchInterview(TestResultPk testResultPk);
	public void modifyInterview(Interview interview);
	public void cancelInterview(int interviewId);
	public TestResult getTestResult(TestResultPk testResultPk);
	public Calendar getHighestDay();
	
	
}
