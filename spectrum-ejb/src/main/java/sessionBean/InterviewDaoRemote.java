package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entities.Interview;

@Remote
public interface InterviewDaoRemote {
	public void addInterview(Interview e);
	public void updateInterview(Interview e);
	public Interview findInterview(int id);
	public void deleteInterview(Interview e);
	public List<Interview> addInterview();

}
