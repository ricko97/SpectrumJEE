package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entities.Question;

@Remote
public interface QuestionDaoRemote {
	public void addQuestion(Question e);
	public void updateQuestion(Question e);
	public Question findQuestion(int id);
	public void deleteQuestion(Question e);
	public List<Question> addQuestion();

}
