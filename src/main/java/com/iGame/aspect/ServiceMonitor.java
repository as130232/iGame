package com.iGame.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect	// AOP
@Component
public class ServiceMonitor {
	
	//execution: 設定AOP 的路徑為哪些，此處為user套件下Service結尾
	@Before("execution(* com.iGame.user.*Service.*(..))")
	public void logServiceAccess(JoinPoint joinPoint){
		try{
			System.out.print("the args is ... : ");
			Object[] args = joinPoint.getArgs();
			Arrays.stream(args).forEach(System.out::println);
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
}
