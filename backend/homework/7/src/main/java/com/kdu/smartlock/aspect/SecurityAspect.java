package com.kdu.smartlock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Around("execution(* com.kdu.smartlock.service.SmartLockService.checkBattery(..))")
    public Object measureBatteryCheckTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Battery check took " + (end - start) + " ms");
        return result;
    }

    @Around("execution(* com.kdu.smartlock.service.SmartLockService.unlock(..)) && args(user)")
    public Object controlAccess(ProceedingJoinPoint joinPoint, String user) throws Throwable {
        if ("Unknown".equals(user)) {
            System.out.println("SECURITY ALERT: Unauthorized access blocked!");
            return null;
        }
        return joinPoint.proceed();
    }
}
