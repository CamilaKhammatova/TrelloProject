package ru.itis.khammatova.impl.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(Log)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Executing method " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        logger.info("Return - " + proceed);
        return proceed;
   }
}
