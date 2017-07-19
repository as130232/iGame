package com.iGame.authentication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iGame.user.domain.User;
import com.iGame.user.domain.UserRepository;
@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
    private UserRepository memberRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = memberRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if(user == null){
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
                gas.add(new SimpleGrantedAuthority("ROLE_USER"));
                
                //這邊的User是UserDetails介面的實作，儲存使用者名稱、密碼及擁有權限
                return new org.springframework.security.core.userdetails.User(
                        user.getEmail(), user.getPassword(), 
                        true, //是否可用
                        true, //是否过期
                        true, //證書不過期為true
                        true, //帳戶未鎖定為true
                        gas);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }

}
