package com.iGame.aspect;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iGame.exception.NoLoggingException;

@Aspect
@Component
public class LoginAspect {

	@AfterThrowing("execution(* com.iGame.authentication.MyAuthenticationProvider.*(..))")
	public void loginAccess(JoinPoint joinPoint){
		try{
			System.out.print("the args is ... : ");
			Object[] args = joinPoint.getArgs();
			Arrays.stream(args).forEach(System.out::println);
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	//未登入時拋出NoLoggingException，接到此例外，轉至登入頁面
	@AfterThrowing(pointcut = "execution(* com.iGame.*.*(..))", throwing = "e")
	public void doLoggingAction(JoinPoint joinPoint, NoLoggingException e){
		
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		try {
			response.sendRedirect("/login");
		} catch (IOException e1) {
			e1.printStackTrace();
		}      
		return;
	}
	
}
