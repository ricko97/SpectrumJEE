package enterpriseServices;

import java.util.List;

import javax.ejb.Remote;

import entities.TestResult;

@Remote
public interface TestResultDaoRemote {
	public void addTestResult(TestResult e);
	public void updateTestResult(TestResult e);
	public TestResult findTestResult(int id);
	public void deleteTestResult(TestResult e);
	public List<TestResult> addTestResult();

}
