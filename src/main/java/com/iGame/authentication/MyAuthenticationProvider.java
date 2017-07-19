package com.iGame.authentication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.iGame.authentication.password.Password;
import com.iGame.authentication.service.LoginAttemptService;
import com.iGame.authentication.service.MyUserDetailsService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
        WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();
        String userIPAddress = wad.getRemoteAddress();
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("userIPAddress: " + userIPAddress + " , username: " + username + " , password: " + password);
        
        if(loginAttemptService.isBlocked(userIPAddress)) {
            throw new LockedException("This ip has been blocked");
        }
        UserDetails user = myUserDetailsService.loadUserByUsername(username);
        
        if(user == null){
            throw new BadCredentialsException("Username not found.");
            
        }

        if (!Password.encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        //將使用者放入Authentication物件，代表已通過驗證
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> authentication) {
        return true;
    }
}