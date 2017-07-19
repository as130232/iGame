package com.iGame.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.iGame.authentication.MyAuthenticationProvider;
import com.iGame.authentication.handler.MyAuthenticationFailureHandler;
import com.iGame.authentication.service.MyUserDetailsService;
import com.iGame.user.domain.UserRepository;

/*
 * @EnableWebSecurity即為Enable SpringSecurityFilterChain，必須override configure方法，
 * 設定網頁存取權限，http物件就是負責這部分，它是一個builder pattern，
 * 目前先呼叫anonymous()使任何人都可以存取網頁
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private MyAuthenticationProvider myAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/hello/**"
				//,"/debit/**"
		
				)
		.authenticated()
		.and()
		.formLogin()
		//.loginPage("/login/login.html");
		.loginPage("/login");
		http.csrf().disable();
		//http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		//http.formLogin().successHandler(authenticationSuccessHandler);
		//http.formLogin().failureHandler(authenticationFailureHandler);
	}
	
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http
	// .authorizeRequests()
	// //定義不需要驗證權限的url
	// .antMatchers(
	// "/", "/*", "/index",
	// "/user", "/users", "/user/*", "/userCondition",
	// "/productOrder", "/productOrders", "/productOrder/*",
	// "/productOrderCondition",
	// "/product", "/products", "/productCondition",
	// "/hello/*"
	// ).permitAll()
	// .anyRequest().authenticated()
	// .and()
	// .formLogin()
	// //.loginPage("/login/login.html")
	// .loginPage("/login")
	// .permitAll()
	// //.failureHandler(myAuthenticationFailureHandler)
	// .and()
	// .logout()
	// .permitAll()
	// .and()
	// //禁用CSRF（跨站點請求偽造）未來改善，example:https://github.com/aditzel/spring-security-csrf-filter
	// .csrf().disable();
	// }

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return myUserDetailsService;
	}

	/*
	 * 身分驗證配置，用於注入自定義身分驗證和密碼校驗規則
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 加載用戶信息
		auth.userDetailsService(myUserDetailsService);
		// 加載授權信息
		auth.authenticationProvider(myAuthenticationProvider);

		auth.inMemoryAuthentication()
		.withUser("user").password("0000").roles("USER")
		.and()
		.withUser("admit").password("0000").roles("ADMIN");
	}
	
	/*
	 * Web層面的配置，一般用来配無需要安全檢查的路徑
	 */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }
	
}