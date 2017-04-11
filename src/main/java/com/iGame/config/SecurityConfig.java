//package com.iGame.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
////	@Override
////	protected void configure(HttpSecurity httpSecurity) throws Exception {
////		httpSecurity.csrf().disable()
////		.authorizeRequests()
////		.antMatchers("/","/home","/loginin").permitAll()
////		.anyRequest().authenticated()
////		.and()
////		//驗證失敗trigger的URL
////		.formLogin()
////		.failureUrl("/login?error=1").permitAll()
////		//成功即導向首頁
////		.defaultSuccessUrl("/")
////		.loginPage("/login").permitAll()
////		.and()
////		//logout setting
////		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////		.logoutSuccessUrl("/login").permitAll();
////	}
////	
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
////		auth.userDetailsService(userDetailsService());
////	}
//	
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeRequests()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().permitAll();
//	}
//	
//	/*
//	 * 自行定義登入帳戶
//	 */
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("test").password("test").roles("USER")
//			.and()
//			.withUser("admin").password("test").roles("USER", "ADMIN");
//			
//	}
//}
