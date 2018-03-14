package com.quickaccount.service.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ServiceLogger {

    private static final Logger LOGGER = Logger.getLogger(ServiceLogger.class);
//    private static File file;
//    private static FileWriter fw;

    @Pointcut("execution(* com.quickaccount.service.*.*(..))")
    public void serviceLoging() {}

    @Around("serviceLoging()")
    public Object logService(ProceedingJoinPoint joinPoint) {
//        try {
//            file = new File("../resources/aspect.log");
//            fw = new FileWriter(file, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String nameSignature = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        LOGGER.info("Start " + nameSignature + " with args " + Arrays.toString(args));

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LOGGER.info("End " + nameSignature + " result - " + result);
        return result;
    }
}
