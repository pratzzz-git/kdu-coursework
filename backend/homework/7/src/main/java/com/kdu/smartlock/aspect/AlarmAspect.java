package com.kdu.smartlock.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AlarmAspect {

    @AfterThrowing(
            pointcut = "execution(* com.kdu.smartlock.service.SmartLockService.*(..))",
            throwing = "ex"
    )
    public void triggerAlarm(Exception ex) {
        System.out.println("ALARM TRIGGERED: System error detected: " + ex.getMessage());
        System.out.println("Emergency service notified");
    }
}
