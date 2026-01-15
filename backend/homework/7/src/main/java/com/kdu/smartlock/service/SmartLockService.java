package com.kdu.smartlock.service;

import org.springframework.stereotype.Service;

@Service
public class SmartLockService {

    public void unlock(String user) {
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
