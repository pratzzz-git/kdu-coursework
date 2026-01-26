package com.kickdrum.smarthome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartHomeApplication {

    private static final Logger log = LoggerFactory.getLogger(SmartHomeApplication.class);

    public static void main(String[] args) {
        log.info("Starting Smart Home Application...");
        SpringApplication.run(SmartHomeApplication.class, args);
    }
}
