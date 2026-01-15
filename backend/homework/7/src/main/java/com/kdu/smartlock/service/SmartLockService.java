package com.kdu.smartlock.service;

import com.kdu.smartlock.exception.HardwareFailureException;
import org.springframework.stereotype.Service;

@Service
public class SmartLockService {

    public void unlock(String user) {
        if (user == null || user.isEmpty()) {
            throw new HardwareFailureException("Hardware malfunction: empty input");
        }
        System.out.println("The door is now open for " + user);
    }

    public void checkBattery() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
