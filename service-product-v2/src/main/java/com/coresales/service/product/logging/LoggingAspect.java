package com.coresales.service.product.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.coresales.service.product.controller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "CONTROLLER");
    }

    @Around("execution(* com.coresales.service.product.service..*(..))")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "SERVICE");
    }

    @Around("execution(* com.coresales.service.product.repository..*(..))")
    public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "REPOSITORY");
    }

    private Object logExecution(ProceedingJoinPoint joinPoint, String layer) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        log.info("[{}] INICIO {}.{}", layer, className, methodName);

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("[{}] FIN {}.{} - {} ms", layer, className, methodName, duration);
            return result;

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            log.error("[{}] ERROR {}.{} - {} ms - {}", layer, className, methodName, duration,
                    e.getMessage(), e);
            throw e;
        }
    }
}