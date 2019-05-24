package enterpriseBeans;



import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Transport;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;

import enterpriseServices.EntrepriseDao;
import enterpriseServices.EntrepriseDaoRemote;
import enterpriseServices.userDao;
import entities.Candidate;
import entities.Coach;
import entities.Enterprise;
import entities.Role;
import entities.Sexe;
import entities.User;
import jobservices.InterviewService;
import jobservices.VerifyRecaptcha;

@ManagedBean
@SessionScoped
public class Enterprisebean {
	/**
	 * 
	 */
	@EJB
	userDao dao;
	@EJB
	EntrepriseDao  dao2 ;
	private Enterprise enterprise;
	private List<Enterprise> enterpriseList;
	private User user=new User();
	private User coworker=new User();
	private List<User> userList;
	private List<User> coworkerList;
	private String mail;
	private int followersNumber;
	private Part imageG;
	private String imageG2;
	//......................................
	private String email;
	private String userName;
	private String firstName;
	private String lastName;
	private String enterprisename;
	private String phone;
	private String domain;
	private String password;
	private String password2;
	private String oldPassword; 
	private String newPassword;
	private String confirmNewPassword;
	private String speciality;
	private String adress;
	private Role role;
	private String admin;
	private String sexe ;
	private String message ;
	private String message2;
	private String message3;
	private String messagelogin;
	//..................
	private User userSignup;
	private String roleSignup="";
	private String passwdConfirm="";
	private int verifNumber;
	private String emailCode="";
	@EJB
	VerifyRecaptcha verifyRecaptcha;
	@EJB
	InterviewService interviewService;
	//..................

	@PostConstruct
	public void init() {
		setUserSignup(new User());
	}
	
	public String signup() {
		User u1=dao.findUserByUserName(userSignup.getUsername());
		User u2=dao.findUserByEmail(userSignup.getEmail());
		if(u1.getUsername().equals(userSignup.getUsername()))
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Username already taken"));
		else if(u2.getEmail().equals(userSignup.getEmail()))
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("This email already exist"));
		else if(!validation_Password(userSignup.getPassword()))
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Please, enter a strong password.\n"
					+ "A digit must occur at least once.\n"
					+ "A lower case letter must occur at least once.\n"
					+ "An upper case letter must occur at least once.\n"
					+ "No whitespace allowed in the entire string.\n"
					+ "A special character must occur at least once.\n"
					+ "At least 8 characters."));
		else if(!userSignup.getPassword().equals(passwdConfirm))
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Passwords are not the same"));
		else if(!("Success").equals(validate_captcha()))
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Incorrect Captcha"));
		else {
			verifNumber = (int) ((Math.random()+17)*100);
			verifNumber+=verifNumber*verifNumber*5;
			interviewService.envoyerMail(userSignup.getEmail(), "Spectrum-Registration", 
					"Registration password is: "+verifNumber+"\n\n"
							+ "Spectrum team.");
			return "validate?faces-redirect=true";
		}
		return null;
	}
	
	public String validateAccount() {
		if (!(Integer.parseInt(emailCode)==verifNumber))
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("You entered a wrong code"));
		else {
			userSignup.setPassword(BCrypt.hashpw(userSignup.getPassword(), BCrypt.gensalt(6)));
			if (roleSignup.equals(Role.candidate.toString())) {
				userSignup.setRole(Role.candidate);
				Candidate c = new Candidate();
				c.setUser(userSignup);
				dao.addCandidate(c);
			}else if(roleSignup.equals(Role.coach.toString())) {
				userSignup.setRole(Role.coach);
				Coach c = new Coach();
				c.setUser(userSignup);
				dao.addCoach(c);
			}else if(roleSignup.equals(Role.enterpriseAdmin.toString())) {
				userSignup.setRole(Role.enterpriseAdmin);
				userSignup.setEnterpriseName(userSignup.getName());
				Enterprise e = new Enterprise();
				e.setName(userSignup.getName());
				e.setUser(userSignup);
				dao.addEnterprise(e);
			}
			return "login?faces-redirect=true";	
		}
		return null;
	}
	
	public String validate_captcha(){
        try {
       String gRecaptchaResponse = FacesContext.getCurrentInstance().
       getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
       boolean verify = verifyRecaptcha.verify(gRecaptchaResponse);
       if(verify){
            return "Success";
       }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage( "Incorrect Captcha") );
            return null;
         }
        } catch (Exception e) {
            System.out.println(e);
        }
       return null;
    }
	public void loadEnterprise(){
		user=dao.findUser(4);
		this.enterprise=dao2.findEnterpriseByName(user.getEnterpriseName());
		//user=dao.findUser(26);
		
		this.enterprise=dao2.findEnterpriseByName(user.getEnterpriseName());
		if(user.getRole()==Role.enterpriseAdmin) {
			admin="true";
		}
		coworkerList=dao.findAllUserByEnterpriseName(user.getEnterpriseName());
		
		//this.user=enterprise.getUser();
		//user.setPhone("23884365");
		//user.setPassword(BCrypt.hashpw("123Azerty@", BCrypt.gensalt(6)));
		//enterprise.setName("spectrum");
		//user.setEnterpriseName("spectrum");
		//dao.updateUser(user);
		//dao2.updateEnterprise(enterprise);
		//user.setName("hjhjhjhj")
		//mail=enterprise.getUser().getEmail();
		
		
	}
	public String singIn2(User u) {
		 
			//this.enterprise=dao2.findEnterpriseByName(u.getEnterpriseName());
			//user=u;
			//return"enterprise/index?faces-redirect=true";
			return "enterprise/index?faces-redirect=true";
		
	}
	public Part getImageG() {
		return imageG;
	}
	public void setImageG(Part imageG) {
		this.imageG = imageG;
	}
	public void loadAllEnterprise() {
		
		this.setEnterpriseList(dao2.findAllEnterprise());
	}
	public void loadAllUser() {
		loadEnterprise();
		this.setUserList(dao.findAlluser());
		setFollowersNumber(userList.size());
		setImageG2("C:/Users/Arshwings/Documents/GitHub/SpectrumJEE2/spectrum-web/src/main/webapp/template/images/"+user.getPicture());
	} 
	public static boolean isValidEmailAddress(String email) { //test @mail
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		      
		   }
		   return result;
		}
	//...................................................................................
	public String login() {
		User u=dao.findUserByUserName(userName);
		if(password.isEmpty()||userName.isEmpty()) {
			messagelogin="Username or password are missing";
			return "login";
		}
		else if (!u.getName().equals(userName)){
			messagelogin="Username or password are wrong";
			return "login";
		}
		else if (!BCrypt.checkpw(password, u.getPassword())){
			messagelogin="Username or password are wrong";
			return "login";
		}
		else  {
			enterprise=dao2.findEnterpriseByName(u.getEnterpriseName());
			user=u;
			
			messagelogin="Done";
			loadEnterprise();
			return "index";
			}
		
		
		
		
	}
	public String persistCoworker() {
		Enterprise e=new Enterprise();
		User u1=new User();
		User u2=new User();
		
		u1=dao.findUserByUserName(userName);
		u2=dao.findUserByEmail(email);
		if(userName.isEmpty()||email.isEmpty()||firstName.isEmpty()||lastName.isEmpty()||password.isEmpty()||password2.isEmpty()||sexe.isEmpty()) {
			setMessage(" Please Complete required information");
			return "";
			//FacesContext.getCurrentInstance().addMessage("singnerror",new FacesMessage(" missing data"));
		}
		else if(u1.getUsername().equals(userName)) {
			setMessage("UserName already in use  try again");
			return "";
		}
		else if (!isValidEmailAddress(email)) {
			setMessage("Not a valide email!! try again");
			return "";
		}
		else if(u2.getEmail().equals(email)) {
			setMessage("Email already in use  try again");
		}
		else if (!password.equals(password2)) {
			setMessage("The two passwords are  not similar  try again");
		}
		else if(!validation_Password(password)) {
			setMessage("Password not valid try again");
		}
		else if(role==null) {
			setMessage("Select the role");
		}
		else {
			User up=new User();
			up.setUsername(userName);
			up.setEmail(email);
			up.setName(firstName);
			up.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(6)));
			up.setPhone(phone);
			up.setRole(role);
			if(sexe.equals("female")) {
			up.setSexe(Sexe.female);
             }
			else {
				up.setSexe(Sexe.male);
			}
			up.setEnterpriseName(enterprise.getName());
			dao.addUser(up);
			setMessage("Done");
			userName="";
			firstName="";
			phone="";
		}	
		return "" ;
			
	}
	public String persistenterprise() {
		Enterprise e=new Enterprise();
		User u1=new User();
		User u2=new User();
		
		u1=dao.findUserByUserName(userName);
		u2=dao.findUserByEmail(email);
		e=dao2.findEnterpriseByName(enterprisename);
		if(userName.isEmpty()||email.isEmpty()||enterprisename.isEmpty()||firstName.isEmpty()||lastName.isEmpty()||password.isEmpty()||password2.isEmpty()||sexe.isEmpty()) {
			setMessage(" Please Complete required information");
			return "singup";
			//FacesContext.getCurrentInstance().addMessage("singnerror",new FacesMessage(" missing data"));
		}
		else if(u1.getUsername().equals(userName)) {
			setMessage("UserName already in use  try again");
			return "singup";
		}
		else if(e.getName().equals(userName)) {
			setMessage("Enterprise Name already in use  try again");
			return "singup";
		}
		
		else if (!isValidEmailAddress(email)) {
			setMessage("Not a valide email!! try again");
			return "singup";
		}
		else if(u2.getEmail().equals(email)) {
			setMessage("Email already in use  try again");
			return "singup";
		}
		else if (!password.equals(password2)) {
			setMessage("The two passwords are  not similar  try again");
			return "singup";
		}
		else if(!validation_Password(password)) {
			setMessage("Password not valid try again");
			return "singup";
		}
		
		else {
			sendEmailBySSl("d4585", "edd_gohan@live.fr");
			User up=new User();
			up.setUsername(userName);
			up.setEmail(email);
			up.setName(firstName);
			up.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(6)));
			up.setPhone(phone);
			up.setRole(Role.enterpriseAdmin);
			if(sexe.equals("female")) {
			up.setSexe(Sexe.female);
             }
			else {
				up.setSexe(Sexe.male);
			}
			e.setName(enterprisename);
			up.setEnterpriseName(e.getName());
			e.setUser(up);
			dao.addUser(up);
			setMessage("Done");
			userName="";
			firstName="";
			phone="";
		}	
		return "" ;
			
	}
	//.............................................................
	public void changePassword() {
		if(oldPassword.isEmpty()||newPassword.isEmpty()||confirmNewPassword.isEmpty()) {
			message2="Please Complete required information ";
			return ;
		}
		else if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
			message2="Wrong old password ";
			return ;
		}
		else if(!newPassword.equals(confirmNewPassword)) {
			setMessage("The two passwords are  not similar  try again");
			return ;
		}
		else if(!validation_Password(newPassword)) {
			setMessage("Password not valid try again");
			return ;
		}
		else {
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(6)));
			dao.updateUser(user);
			setMessage(" Success");
			confirmNewPassword="";role=null;
			return ;
		}	
	}
	public void updateUser() {
		
		if(!enterprisename.isEmpty()) {
			if(confirmNewPassword.isEmpty()) {
				setMessage3("Write password");
			}
		}
		if(!adress.isEmpty()) {
			user.setAddress(adress);
			if(confirmNewPassword.isEmpty()) {
				setMessage3("Write password");
			}
		}
		if(!phone.isEmpty()) {
			user.setPhone(phone);
			if(confirmNewPassword.isEmpty()) {
				setMessage3("Write password");
			}
		}
		if (confirmNewPassword.isEmpty()&&!BCrypt.checkpw(oldPassword, user.getPassword())) {
			setMessage3("Wrong old password ");
			return ;
		}
		else {
			dao.updateUser(user);
			setMessage3("Change done ");
			userName="";
			firstName="";
			phone="";
		}
		
		
	}
	public static Boolean sendEmailBySSl(String code, String userEmail) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("safepetrol@gmail.com", "startxx2019");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pidev.Cupcakes@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
			message.setSubject("Validation Code ");

			message.setText(" your account validation code is:" + code + "\n\n\n\n Wellcome to Spectrum");
			Transport.send(message);
			System.out.println(" code send is:" + code);

			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	public void removeuser(User u) {
		
		mail=u.getEmail();
		dao.deleteUser(u.getId());
		//coworkerList=dao.findAllUserByEnterpriseName("spectrum");
	}
	public String inspect(User u) {
		coworker=u;
		return "coworker-profile?faces-redirect=true";
	}
	public void upllychange(){
		if(role!=coworker.getRole()) {
			coworker.setRole(role);
			dao.updateUser(coworker);
			role=null;
		}
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getMail() {
		return mail; 
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<Enterprise> getEnterpriseList() {
		return enterpriseList;
	}
	public void setEnterpriseList(List<Enterprise> enterpriseList) {
		this.enterpriseList = enterpriseList;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	public int getFollowersNumber() {
		return followersNumber;
	}
	public void setFollowersNumber(int followersNumber) {
		this.followersNumber = followersNumber;
	}
	public void addGas() {
		String uuu = UUID.randomUUID().toString();
		
		Path from = Paths.get("C:/Users/Arshwings/Desktop/"+imageG.getSubmittedFileName());
		Path to = Paths.get("C:/Users/Arshwings/Documents/GitHub/SpectrumJEE2/spectrum-web/src/main/webapp/template/images/" + uuu + ".jpeg");
		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES };

		try {
			Files.copy(from, to, options);

		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setPicture(uuu+".jpeg");
		dao.updateUser(user);
	}
	public String getImageG2() {
		return imageG2;
	}
	public void setImageG2(String imageG2) {
		this.imageG2 = imageG2;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getMessage2() {
		return message2;
	}
	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	public String getMessage3() {
		return message3;
	}
	public void setMessage3(String message3) {
		this.message3 = message3;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public List<User> getCoworkerList() {
		return coworkerList;
	}
	public void setCoworkerList(List<User> coworkerList) {
		this.coworkerList = coworkerList;
	}
	public String getEnterprisename() {
		return enterprisename;
	}
	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}
	public User getCoworker() {
		return coworker;
	}
	public void setCoworker(User coworker) {
		this.coworker = coworker;
	}
	public String getMessagelogin() {
		return messagelogin;
	}
	public void setMessagelogin(String messagelogin) {
		this.messagelogin = messagelogin;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getRoleSignup() {
		return roleSignup;
	}

	public void setRoleSignup(String roleSignup) {
		this.roleSignup = roleSignup;
	}

	public User getUserSignup() {
		return userSignup;
	}
	public void setUserSignup(User userSignup) {
		this.userSignup = userSignup;
	}

	public String getPasswdConfirm() {
		return passwdConfirm;
	}

	public void setPasswdConfirm(String passwdConfirm) {
		this.passwdConfirm = passwdConfirm;
	}

	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

}
