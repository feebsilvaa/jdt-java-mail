package br.com.feedev.jdt_javamail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	protected static final String MAIL_USERNAME = "feedev.jdt@gmail.com";
	protected static final String MAIL_PASSWORD = "@jdt12345678";

	@Test
	public void testeEmail() {
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true"); // autorização
			props.put("mail.smtp.starttls", "true"); // autenticacao
			props.put("mail.smtp.host", "smtp.gmail.com"); // servidor gmail google
			props.put("mail.smtp.port", "465"); // porta do servidor
			props.put("mail.smtp.socketFactory.port", "465"); // porta do socket
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // classe socket de conexão ao SMTP
		
			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MAIL_USERNAME, MAIL_PASSWORD);
				}
			});

			String toMails = "bsilva.fe@gmail.com, nandoossilva2@gmail.com, fernando.barbosa@meta.com.br";
			Address[] toAddress = InternetAddress.parse(toMails);
					
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(MAIL_USERNAME));
			message.setRecipients(Message.RecipientType.TO, toAddress);
			message.setRecipients(Message.RecipientType.CC, null);
			message.setRecipients(Message.RecipientType.BCC, null);
			message.setSubject("Envio de e-mail com a API JavaMail");
			message.setText("Olá programador, você acaba de receber um e-mail enviado com a API JavaMail.");
			
			Transport.send(message);
			
		} catch (AuthenticationFailedException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	
	}
	
}
