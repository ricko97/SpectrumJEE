package services;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailSender implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  void sendMail(String fromMail,String username,String password,
            String toMail,String subject,String message) throws AddressException, MessagingException{
    
        Properties props=System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        
        
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.fallback", "false");
                
                Session mailSession=Session.getDefaultInstance(props, null);
                mailSession.setDebug(true);
                
                Message mailMessage=new MimeMessage(mailSession);
                
                mailMessage.setFrom(new InternetAddress(fromMail));
                mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
                mailMessage.setContent(message,"text/html");
                mailMessage.setSubject(subject);
                
                Transport transport=mailSession.getTransport("smtp");
                transport.connect("smtp.gmail.com", username, password);
                transport.sendMessage(mailMessage, mailMessage.getAllRecipients());   
                
                
    }
    
}
