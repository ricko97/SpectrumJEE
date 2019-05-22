package coach;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
import javax.persistence.TypedQuery;

import entities.Candidate;
import entities.Coaching;
import entities.Course;
import entities.Post;
import entities.Skill;
import entities.User;

@Stateless
public class Register implements RegisterLocal, RegisterRemote {
	
	@PersistenceContext(unitName="spectrum-ejb")
	private EntityManager em;
	/////////////////////MANAGE COACH(ADD NEW COACH)/////////////////////////
	
	@Override
	public  void login(String login , String pass) {
		/*Query q = em.createQuery("select e from user e where e.username=:login and e.password=:pass");
				q.setParameter("login", login).setParameter("pass", pass);
				System.out.println(q);*/
	}
	@Override
	public  boolean usernamebase(String username) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM user u where u.username= :username",User.class);
		query.setParameter("username", username);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			System.out.println("no result found");
		}
		return false;
	}
	
	@Override
	public  User usernamebase2(String username) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM user u where u.username= :username",User.class);
		return query.setParameter("username", username).getSingleResult();
			
		
	}


	@Override
	public  boolean passwordbase(String password) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM user u where u.password= :password",User.class);
		query.setParameter("password", password);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			System.out.println("no result found");
		}
		return false;
	}
	//______________________________________________________________________________
	/**
	 * Validation Password     */
	//______________________________________________________________________________
	

	private static boolean validation_Password(final String PASSWORD_Arg)    {
	    boolean result = false;
	    try {
	        if (PASSWORD_Arg!=null) {
	            //_________________________
	            //Parameteres
	            final String MIN_LENGHT="8";
	            final String MAX_LENGHT="20";
	            final boolean SPECIAL_CHAR_NEEDED=true;

	            //_________________________
	            //Modules
	            final String ONE_DIGIT = "(?=.*[0-9])";  //(?=.*[0-9]) a digit must occur at least once
	            final String LOWER_CASE = "(?=.*[a-z])";  //(?=.*[a-z]) a lower case letter must occur at least once
	            final String UPPER_CASE = "(?=.*[A-Z])";  //(?=.*[A-Z]) an upper case letter must occur at least once
	            final String NO_SPACE = "(?=\\S+$)";  //(?=\\S+$) no whitespace allowed in the entire string
	            //final String MIN_CHAR = ".{" + MIN_LENGHT + ",}";  //.{8,} at least 8 characters
	            final String MIN_MAX_CHAR = ".{" + MIN_LENGHT + "," + MAX_LENGHT + "}";  //.{5,10} represents minimum of 5 characters and maximum of 10 characters

	            final String SPECIAL_CHAR;
	            if (SPECIAL_CHAR_NEEDED==true) SPECIAL_CHAR= "(?=.*[@#$%^&+=])"; //(?=.*[@#$%^&+=]) a special character must occur at least once
	            else SPECIAL_CHAR="";
	            //_________________________
	            //Pattern
	            //String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	            final String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;
	            //_________________________
	            result = PASSWORD_Arg.matches(PATTERN);
	            //_________________________
	        }    

	    } catch (Exception ex) {
	        result=false;
	    }

	    return result;
	}  
	
	
	
	@Override

	public void createCoach(User coach) {
		em.persist(coach);
		

	}
	

	
	
	
	
	
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);
	}
	@Override
	public Candidate findCandidateById(int id) {
		// TODO Auto-generated method stub
		return em.find(Candidate.class, id);
	}
	
	@Override
	public void update(User u) {
		u=findById(u.getId());
		em.merge(u);
	}
	
	
	@Override
	public List<User> findAllUsers(int id) {
		List<User> users = em.createQuery("SELECT c FROM user c WHERE c.id=:id", User.class).getResultList();
		
		System.out.println(users);
		
		return users;
	} 
	
	@Override
	public void updatepara(User p ) {

		em.merge(p);
	}
	
	
	

	@Override
	public User getUserByEmailAndPassword(String username, String password) {
		TypedQuery<User> query = em.createQuery("SELECT e FROM user e " + 
									"WHERE e.username=:username AND e.password=:password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
		//	System.out.println
			Logger.getAnonymousLogger().info("Aucun user trouvé ayant username: " + username);
		}
		
		return user;
	}
	
	@Override
	public User getUserPassword( String password) {
		TypedQuery<User> query = em.createQuery("SELECT e FROM user e " + 
									"WHERE  e.password=:password", User.class);
		query.setParameter("password", password);
		
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
		//	System.out.println
			Logger.getAnonymousLogger().info("Aucun user trouvé ayant password: " + password);
		}
		
		return user;
	}
	
	
	/////////////////////MANAGE PARAMETRES///////////////////

	@Override
	public void updateparameter(String email,int userId ) {
		User user = em.find(User.class, userId);
		user.setEmail(email);
	
	}

	
	@Override
	public User getUserDetails(int id) {
		
			User userDetails = em.find(User.class, id);
			return userDetails;
		
	}

	@Override
	public void updateUserDetails(User userDetails) {
			User details = getUserDetails(userDetails.getId());
			details.setUsername(userDetails.getUsername());
			details.setEmail(userDetails.getEmail());
			details.setPassword(userDetails.getPassword());
		
	}
	
	
	
	
	
	
	
	/////////////////////MANAGE COURSE/////////////////////////
	

	
	
	
	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		 return em.createQuery("select e from course e", Course.class).getResultList();
	}
	
	
	@Override

	public void addCourse(Course course) {
		em.persist(course);
		

	}
	
	/*@Override
	public void deletecourse(int id) {

		em.remove(em.find(Course.class,id));
	}*/
	
	@Override
	public void deletecourse(Course p ) {

		em.remove(em.merge(p));
	}
	@Override
	public void updatecourse(Course p ) {

		em.merge(p);
	}

	@Override
	public Course getCourseDetails(int id) {
		
			Course courseDetails = em.find(Course.class, id);
			return courseDetails;
		
	}

	@Override
	public void updateCourseDetails(Course courseDetails) {
			Course details = getCourseDetails(courseDetails.getId());
			details.setDescription(courseDetails.getDescription());
			details.setAdded_at(courseDetails.getAdded_at());
		
	}
	
	
	
	
	@Override
	public List<Course> findBydesc(String description) {
			
		return em.createQuery("Select e From course e WHERE e.description= :description ", Course.class).getResultList();

		}
	
	 
		@Override
		public List<Course> getAllCourse() {
			List<Course> emp = em.createQuery("Select e from course e", Course.class).getResultList();
			return emp;
		}
	/////////////////////MANAGE OFFRES_COACHING///////////////
	
	public void addoffCoaching(Coaching coaching) {
		em.persist(coaching);
		

	}
	
	@Override
	public List<Coaching> findAllcoaching() {
		// TODO Auto-generated method stub
		 return em.createQuery("select e from Coaching e", Coaching.class).getResultList();
	}
	
	@Override
	public void deleteoffCoaching(int id) {

		em.remove(em.find(Coaching.class,id));
	}
	
	
	
	
	@Override
	public Coaching getCoachingDetails(int id) {
		
			Coaching coachingDetails = em.find(Coaching.class, id);
			return coachingDetails;
		
	}

	@Override
	public void updateCoachingDetails(Coaching CoachingDetails) {
		Coaching details = getCoachingDetails(CoachingDetails.getId());
			details.setTitle(CoachingDetails.getTitle());
			details.setStart(CoachingDetails.getStart());
			details.setEnd(CoachingDetails.getEnd());

	}
	@Override
	public void deletecoaching(Coaching p ) {

		em.remove(em.merge(p));
	}
	@Override
	public void updatecoaching(Coaching p ) {

		em.merge(p);
	}
	
	
	///////////////////////Repport PDF/////////////////////////
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllSentReports(User user) {
		// TODO Auto-generated method stub
		String req = "select r from user r ";
		Query query = em.createQuery(req);
		return query.getResultList();
	}
	
	@Override
	public List<User> getAllReceivedReports(User user) {
		// TODO Auto-generated method stub
		String req = "select r from user r ";
		Query query = em.createQuery(req);
		return query.getResultList();
	}
	
	@Override
	public void sendReport(User r) {
		// TODO Auto-generated method stub
		//r.setRepportDate(new Date());
		em.persist(r);
	}
	
	
	@Override
	public void deleteUser(int id) {

		em.remove(em.find(Post.class,id));
	}
	/////////////////////MANAGE POSTS/////////////////////////
	public void addposts(Post post) {
		em.persist(post);
		
	}
	
	
	@Override
	public void deleteposts(int id) {

		em.remove(em.find(Post.class,id));
	}
	
	
	@Override
	public Post getPostDetails(int id) {
		
		Post postDetails = em.find(Post.class, id);
			return postDetails;
		
	}

	@Override
	public void updatePostDetails(Post postDetails) {
		  Post details = getPostDetails(postDetails.getId());
			details.setContent(postDetails.getContent());
		
	}
	
	
	
	
	  /////////////////////MANAGE Skills/////////////////////////
	public void addskills(Skill skill) {
		em.persist(skill);
		

	}
	
	@Override
	public void deleteskills(int id) {

		em.remove(em.find(Skill.class,id));
	}
	
	@Override
	public Skill getSkillDetails(int id) {
		
		Skill skillDetails = em.find(Skill.class, id);
			return skillDetails;
		
	}

	@Override
	public void updateSkillDetails(Skill skillDetails) {
		Skill details = getSkillDetails(skillDetails.getId());
			details.setLabel(skillDetails.getLabel());
		
	}

}
