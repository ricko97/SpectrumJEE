package coachBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import coach.MailSender;
import coach.Register;
import coach.RegisterLocal;
import coach.RegisterRemote;
import entities.User;


@ManagedBean
@ViewScoped
public class CompteRenduBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private RegisterRemote rml = new Register();
	private User user = new User();
	private String error = "";
	//private User user = LoginBean.loggedUser;
	private Part picDisease;
	private User selectedReport;
	private HttpServletRequest req;
	
	public String doGoToReport(){
			return "/analytics/sent_repports?faces-redirect=true";
	}
	
	public List<User> doListSentRepports() {
		return rml.getAllSentReports(user);
	}
	
	public List<User> doListReceivedRepports() {
		return rml.getAllReceivedReports(user);
	}
	
		
	public void doUpload() {
		try {
			InputStream in = picDisease.getInputStream();
			String pathname = "C:\\Users\\ivano\\Documents\\GitHub\\JavaEEPI\\pidev_epione-web\\src\\main\\webapp\\resources\\upload\\";
			File pic = new File(pathname + picDisease.getSubmittedFileName());
//			System.out.println(picDisease.getSubmittedFileName());
			pic.createNewFile();
			FileOutputStream picOut = new FileOutputStream(pic);
			byte[] buffer = new byte[1024]; //1073741824 - 3145728
			int length;
			while ((length=in.read(buffer))>0) {
				picOut.write(buffer, 0, length);				
			}
			user.setPicture(picDisease.getSubmittedFileName());
			picOut.close();
			in.close();
			picDisease.write(pathname+picDisease.getSubmittedFileName());
//			System.out.println("Okay");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur image : " );
			e.printStackTrace(System.out);
		}
	}
	
    public void buttonAction(String name) {
        addMessage("Your report has been sent to Dr. "+name);
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	
	public String doSendReport() throws ServletException,IOException {
		try {
			req = null;
			doUpload();
			rml.sendReport(user);
//			if(req.getParameter("notif") != null) {
//				System.out.println("True : checked");
//			} else {
//				System.out.println("False : !!unchecked");
//			}
			//buttonAction(user.getReferentDoctor());
			return "/analytics/sent_repports?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.error = e.getMessage();
			System.out.println("Erreur envoi : " + error);
			return null;
		}		
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Part getPicDisease() {
		return picDisease;
	}

	public void setPicDisease(Part picDisease) {
		this.picDisease = picDisease;
	}

	public User getReport() {
		return user;
	}

	public void setReport(User r) {
		this.user = r;
	}

	public User getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(User selectedReport) {
		this.selectedReport = selectedReport;
	}
	
	
	
	/*   API Mail   */
	
	
	
    private String fromMail;
    private String username;
    private String password;
    private String toMail;
    private String subject;
    private String message;

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void send() {
        try {
            MailSender mailSender = new MailSender();
            mailSender.sendMail(fromMail, username, password, toMail, subject, message);
        } catch (Exception e) {
        }
    }


}
