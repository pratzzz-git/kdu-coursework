package com.kickdrum.smarthome.service.impl;

import com.kickdrum.smarthome.entity.User;
import com.kickdrum.smarthome.exception.BusinessException;
import com.kickdrum.smarthome.repository.UserRepository;
import com.kickdrum.smarthome.service.AuthService;
import com.kickdrum.smarthome.util.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private static final Logger log =
            LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String email, String rawPassword) {

        log.info("Attempting to register user with email: {}", email);

        if (userRepository.existsByEmailAndDeletedDateIsNull(email)) {
            log.warn("Registration failed. User already exists with email: {}", email);

            throw new BusinessException(
                    ErrorCode.USER_ALREADY_EXISTS,
                    "User already exists with this email"
            );
        }

        String encryptedPassword = passwordEncoder.encode(rawPassword);

        User user = new User(email, encryptedPassword);

        User savedUser = userRepository.save(user);

        log.info("User registered successfully with id: {}", savedUser.getId());

        return savedUser;
    }

    @Override
    public User authenticate(String email, String rawPassword) {

        log.info("Authentication attempt for email: {}", email);

        User user = userRepository
                .findByEmailAndDeletedDateIsNull(email)
                .orElseThrow(() -> {
                    log.warn("Authentication failed. User not found for email: {}", email);
                    return new BusinessException(
                            ErrorCode.AUTH_FAILED,
                            "Invalid email or password"
                    );
                });

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            log.warn("Authentication failed. Password mismatch for email: {}", email);

            throw new BusinessException(
                    ErrorCode.AUTH_FAILED,
                    "Invalid email or password"
            );
        }

        log.info("Authentication successful for user id: {}", user.getId());

        return user;
    }
}
