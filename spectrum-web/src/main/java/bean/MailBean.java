package bean;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.j2ee.statistics.MessageDrivenBeanStats;


@ManagedBean//(name = "dispoBean") 
@ViewScoped
public class MailBean {
	public String name;
	public String header;
	public String mailAdress;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}

	public void sendMail()
	{
		final String username="saadallahmanell@gmail.com";
		final String password="06101996";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		props.put("mail.protocol.ssl.trust","*");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.ssl.trust","smtp.gmail.com");

		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
		}
		});
		try {
			Message message= new MimeMessage(session);
			message.setFrom(new InternetAddress("aa@aa.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("saadallahmanell@gmail.com"));
		
		    message.setSubject("Spectrum Mail");
		    message.setText(name+"'send your message from"+mailAdress+" whicch header"+ header);
		    message.setText(mailAdress+"     "+ name +"                  "+ header);
		    Transport.send(message);
		} catch (MessagingException ex) {
			// TODO: handle exception
			throw new RuntimeException(ex);
		}
	}
}
