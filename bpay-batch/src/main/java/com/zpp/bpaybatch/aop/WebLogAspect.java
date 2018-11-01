package com.zpp.bpaybatch.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ThreadLocal<Long> startTime = new ThreadLocal<>();
	private ThreadLocal<Long> endTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.zpp.bpaybatch..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		Long start = System.currentTimeMillis();
		startTime.set(start);
		logger.info("CLASS_METHOD : {} begin at {} ms.",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
				startTime.get());
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		Long end = System.currentTimeMillis();
		endTime.set(end);
		logger.info("RESPONSE : {} end in {} ms, with time {} ms.", ret, endTime.get(), endTime.get() - startTime.get());
	}

}
