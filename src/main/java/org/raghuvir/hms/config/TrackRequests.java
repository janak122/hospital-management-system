package org.raghuvir.hms.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
public class TrackRequests {
	@Pointcut("execution(* org.raghuvir.hms.controllers..*(..))||"
			+ "execution(* org.raghuvir.hms.daos..*(..))||"
			+ "execution(* org.raghuvir.hms.services..*(..))")
	public void all(){}//pointcut name
	
	@Around("all()")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable 
	{
		System.out.println("===============>"+pjp.getSignature());
		Object obj=pjp.proceed();
		System.out.println("<==============="+pjp.getSignature());
		return obj;
	}
}
