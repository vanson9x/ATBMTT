package Design;

import java.sql.Connection;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Connection.MailClient;

public class Main {
	private static Login login;
	private static SignIn sign;
	private static SendCode code;
	private static ResetPassword reset;
	
	public static void main(String[] args) {
		Login();
	}
	
	public static void Login(){
		try{
			sign.setVisible(false);
		}catch(Exception e){}
		login = new Login();
		login.setVisible(true);
	}
	
	public static void SignIn(){
		try{login.setVisible(false);
		}catch(Exception e){}
		
		sign = new SignIn();
		sign.setVisible(true);
	}
	
	public static void SendCode(){
		try{login.setVisible(false);
		}catch(Exception e){}
		
		code = new SendCode();
		code.setVisible(true);
	}
	
	public static void ResetPassword(){
		try{code.setVisible(false);
		}catch(Exception e){}
		
		reset = new ResetPassword();
		reset.setVisible(true);
	}
}
