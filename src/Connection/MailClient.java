package Connection;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class MailClient {
	
	public static void sendMail(final String ID, final String password, String recipient, String subject, String content){
		Properties pro = System.getProperties();
		pro.put("mail.smtp.host","smtp.gmail.com");
		pro.put("mail.smtp.auth","true");
		pro.put("mail.smtp.port","465");
		pro.put("mail.smtp.socketFactory.class",javax.net.ssl.SSLSocketFactory.class.getName());
		
		Session session = Session.getDefaultInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(ID,password);
			}
		});
		Message messenge = new MimeMessage(session);
		try {
			messenge.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			messenge.setSubject(subject);
			messenge.setContent("Code confirm: " + content + "\n" + new Date(),"text/html");
			Transport.send(messenge);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}	
	}
}
