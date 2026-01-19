package com.kdu.eventsphere.config;

import com.kdu.eventsphere.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(com.kdu.eventsphere.repository.UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.count() == 0) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");

                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole("USER");

                userRepository.save(admin);
                userRepository.save(user);
            }
        };
    }
}
