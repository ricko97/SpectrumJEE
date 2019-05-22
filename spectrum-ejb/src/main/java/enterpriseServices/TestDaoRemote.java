package enterpriseServices;

import java.util.List;

import javax.ejb.Remote;

import entities.Test;

@Remote
public interface TestDaoRemote {
	public void addTest(Test e);
	public void updateTest(Test e);
	public Test findTest(int id);
	public void deleteTest(Test e);
	public List<Test> addTest();

}
