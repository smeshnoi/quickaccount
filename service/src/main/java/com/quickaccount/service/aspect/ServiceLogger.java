package com.quickaccount.service.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ServiceLogger {

    private static final Logger LOG = Logger.getLogger(ServiceLogger.class);

    @Pointcut("execution(* com.quickaccount.service.*.*(..))")
    public void serviceLoging() {}

    @Around("serviceLoging()")
    public Object logService(ProceedingJoinPoint joinPoint) {
        String nameSignature = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        LOG.info("Start " + nameSignature + " with args " + Arrays.toString(args));
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LOG.info("End " + nameSignature + " result - " + result);
        return result;
    }
}
