package coach;

import java.util.List;

import javax.ejb.Remote;

import entities.Candidate;
import entities.Coaching;
import entities.Course;
import entities.Post;
import entities.Skill;
import entities.User;

@Remote
public interface RegisterRemote {
	Candidate findCandidateById(int id);

	void createCoach(User coach);

	List<User> findAllUsers(int id);

	void updateparameter(String email, int userId);

	void login(String login, String pass);

	User findById(int id);

	void update(User u);

	void addCourse(Course course);

	void addoffCoaching(Coaching coaching);

	void addposts(Post post);

	void addskills(Skill skill);

	void deleteoffCoaching(int id);

	void deleteposts(int id);

	void deleteskills(int id);

	User getUserDetails(int id);

	void updateUserDetails(User userDetails);

	Course getCourseDetails(int id);

	void updateCourseDetails(Course courseDetails);

	Coaching getCoachingDetails(int id);

	void updateCoachingDetails(Coaching CoachingDetails);

	Post getPostDetails(int id);

	void updatePostDetails(Post postDetails);

	Skill getSkillDetails(int id);

	void updateSkillDetails(Skill skillDetails);

	List<Course> findAll();

	List<Coaching> findAllcoaching();

	void deletecourse(Course p);

	void deletecoaching(Coaching p);

	void updatecoaching(Coaching p);

	void updatecourse(Course p);

	void updatepara(User p);

	List<Course> findBydesc(String desc);

	boolean usernamebase(String username);

	boolean passwordbase(String password);

	User usernamebase2(String username);

	User getUserByEmailAndPassword(String email, String password);

	List<User> getAllSentReports(User user);
	List<User> getAllReceivedReports(User user);
	void sendReport(User r);
	void deleteUser(int id);
	User getUserPassword(String password);
	List<Course> getAllCourse();


}
