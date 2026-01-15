package com.kdu.smartlock.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @Before("execution(* com.kdu.smartlock.service.SmartLockService.unlock(..))")
    public void logAccessAttempt() {
        System.out.println("ACCESS ATTEMPT: User is approaching the door");
    }

    @AfterReturning("execution(* com.kdu.smartlock.service.SmartLockService.unlock(..))")
    public void logSuccess() {
        System.out.println("SUCCESS: User has entered the building");
    }
}
