package com.kickdrum.smarthome.controller.auth;

import com.kickdrum.smarthome.dto.request.auth.LoginRequest;
import com.kickdrum.smarthome.dto.request.auth.RegisterRequest;
import com.kickdrum.smarthome.dto.response.ApiResponse;
import com.kickdrum.smarthome.dto.response.auth.LoginResponse;
import com.kickdrum.smarthome.entity.User;
import com.kickdrum.smarthome.security.JwtUtil;
import com.kickdrum.smarthome.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest request) {

        log.info("Received registration request for email: {}", request.getEmail());

        authService.register(request.getEmail(), request.getPassword());

        log.info("Registration successful for email: {}", request.getEmail());

        return ApiResponse.success("User registered successfully");
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {

        log.info("Login attempt for email: {}", request.getEmail());

        User user =
                authService.authenticate(
                        request.getEmail(),
                        request.getPassword()
                );

        String token =
                jwtUtil.generateToken(
                        user.getId(),
                        user.getEmail()
                );

        log.info("Login successful for user id: {}", user.getId());

        return ApiResponse.success(new LoginResponse(token));
    }
}
