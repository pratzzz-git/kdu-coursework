package com.kickdrum.smarthome.service;

import com.kickdrum.smarthome.entity.User;

public interface AuthService {

    User register(String email, String rawPassword);

    User authenticate(String email, String rawPassword);
}
