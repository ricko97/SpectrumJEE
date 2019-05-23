package entities;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {
	   
	private String username = "saadallahmanell@gmail.com";
	private String password = "06101996";

	public void envoyer(String text,String from,String to) {
	// Etape 1 : Création de la session
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
	// Etape 2 : Création de l'objet Message
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(from));
	message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
	message.setSubject("Spectrum Mail");
	message.setText(text);
	// Etape 3 : Envoyer le message
	Transport.send(message);
	System.out.println("Message_envoye");
	} catch (MessagingException e) {
	throw new RuntimeException(e);
	} }
}
