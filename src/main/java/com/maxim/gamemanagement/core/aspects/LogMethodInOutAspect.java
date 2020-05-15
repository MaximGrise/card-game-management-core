package com.maxim.gamemanagement.core.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogMethodInOutAspect {

  private final Logger logger = LoggerFactory.getLogger(LogMethodInOutAspect.class);

  @Around("@annotation(LogMethodInOut)")
  public Object logMethodInOut(ProceedingJoinPoint joinPoint) throws Throwable {
    logger.debug("Entering {}", joinPoint.getSignature());
    try {
      joinPoint.proceed();
    } catch (Throwable e) {
      logger.debug(String.format("Exception in %s", joinPoint.getSignature()), e);
    }
    logger.debug("Leaving {}", joinPoint.getSignature());
    return joinPoint;
  }
}