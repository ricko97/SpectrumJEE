package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entities.TestResultPk;

@Remote
public interface TestResultPkDaoRemote {
	public void addTestResultPk(TestResultPk e);
	public void updateTestResultPk(TestResultPk e);
	public TestResultPk findTestResultPk(int id);
	public void deleteTestResultPk(TestResultPk e);
	public List<TestResultPk> addTestResultPk();

}
