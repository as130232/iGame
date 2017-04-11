//package com.iGame.authentication;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.iGame.member.Member;
//import com.iGame.member.MemberRepository;
//@Service("MyUserDetailsImpl")
//public class MyUserDetailsService implements UserDetailsService{
//	@Autowired
//    private MemberRepository memberRepository;
//
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Member user;
//        try {
//            user = memberRepository.findByEmail(userName);
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("user select fail");
//        }
//        if(user == null){
//            throw new UsernameNotFoundException("no user found");
//        } else {
//            try {
//                List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
//                gas.add(new SimpleGrantedAuthority("ROLE_USER"));
//                return new org.springframework.security.core.userdetails.User(
//                        user.getUsername(), user.getPassword(), true, true, true, true, gas);
//            } catch (Exception e) {
//                throw new UsernameNotFoundException("user role select fail");
//            }
//        }
//    }
//
//}
