package com.kickdrum.smarthome.repository;

import com.kickdrum.smarthome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndDeletedDateIsNull(String email);

    boolean existsByEmailAndDeletedDateIsNull(String email);

    Optional<User> findByIdAndDeletedDateIsNull(Long id);
}
