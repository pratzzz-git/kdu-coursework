package com.kdu.hospital.service;

import com.kdu.hospital.entity.User;
import com.kdu.hospital.exception.InvalidPageSizeException;
import com.kdu.hospital.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(int page, int size) {

        if (size < 1 || size > 50) {
            throw new InvalidPageSizeException("Page size must be between 1 and 50");
        }

        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
