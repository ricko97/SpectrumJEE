package coachBeans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;
import coach.RegisterLocal;


@ManagedBean
@SessionScoped
public class MedecinBean implements Serializable{
	private int id; 
	private String address; 
	private Date birth;
	private String Name; 
	
	private String password; 
	private boolean logged_In;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	public boolean isLogged_In() {
		return logged_In;
	}




	public void setLogged_In(boolean logged_In) {
		this.logged_In = logged_In;
	}

	private List<User> listemed=new ArrayList<User>();
	private User user =new User();
	@PersistenceContext
	EntityManager em;
	@EJB
	private RegisterLocal RegisterLocal;
	
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
	
	
	
	
	public String verifierpass(){
		String navigate_To = "null";
		//user = RegisterLocal.getUserPassword( password);
		
		if(!validation_Password(password)){
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(6)));
			//password =BCrypt.hashpw("string", BCrypt.gensalt(6));
			RegisterLocal.createCoach(user);
			navigate_To = "/pages/login2?faces-redirect=true";
			setLogged_In(true);
		}else{
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad credentials"));
		}
		
		return navigate_To;
	}
	
	
	
	 private String getCryptedPassword(String notCryptedPassword) {
	        MessageDigest md=null;
	        try {
	            md = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException ex) {
	            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        if (md == null)
	            return notCryptedPassword;
	 
	        md.update(notCryptedPassword.getBytes());
	 
	        byte input[] = md.digest();
	 
	        // Convert the byte variable to hexadecimal format
	        StringBuilder hexaString = new StringBuilder();
	    	for (int i=0;i<input.length;i++) {
	    		String hexaChar=Integer.toHexString(0xff & input[i]);
	   	     	if(hexaChar.length()==1) hexaString.append('0');
	   	     	hexaString.append(hexaChar);
	    	}
	        return hexaString.toString();
	    }

	
	
	
	
	public List<User> getListemed() {
		return listemed;
	}

	public void setListemed(List<User> listemed) {
		this.listemed = listemed;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	
	
	public void doAdd()
	{
		RegisterLocal.createCoach(user);
	}
	
	public void removeUser(int id) {
		RegisterLocal.deleteUser(id);
	}
	
	
	public void updateUser(User user){
		 id=user.getId();
		 address=user.getAddress();
		 birth=user.getBirth();
		 Name=user.getName();
		 
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	
	
	
	
	
	
}
