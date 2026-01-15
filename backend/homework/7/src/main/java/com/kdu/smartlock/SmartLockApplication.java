package com.kdu.smartlock;

import com.kdu.smartlock.service.SmartLockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartLockApplication.class, args);
    }

    @Bean
    CommandLineRunner run(SmartLockService smartLockService) {
        return args -> {
            smartLockService.checkBattery();
            smartLockService.unlock("Guest");
            smartLockService.unlock("Unknown");
        };
    }

}
