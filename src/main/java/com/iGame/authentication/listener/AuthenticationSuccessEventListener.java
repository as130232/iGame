package com.iGame.authentication.listener;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.iGame.authentication.service.LoginAttemptService;
import com.iGame.email.SmtpMailService;

@Component
public class AuthenticationSuccessEventListener
        implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private SmtpMailService smtpMailService;
    
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();
        //將block中登入失敗的ip移除
        loginAttemptService.loginSucceeded(auth.getRemoteAddress());
        
        //寄email，表示登入成功
//        String to = "as130232@gmail.com";
//        String subject = "Login Success!";
//        String content = "安安你好";
        
     
		//smtpMailService.send(to, subject, content);
		
    }
}
