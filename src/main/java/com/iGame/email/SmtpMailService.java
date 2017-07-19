package com.iGame.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpMailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String subject, String content){
		try{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			//javaMailSender.send(mimeMessage);
			System.out.println("Mail :　Send Succes!");
			
		}catch (MessagingException e){
		     System.out.println("Mail :　Send Fail!");
		     e.printStackTrace();
	    }
	}
	
}
