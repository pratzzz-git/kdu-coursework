package com.kickdrum.smarthome.repository;

import com.kickdrum.smarthome.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {

    Optional<House> findByIdAndDeletedDateIsNull(Long id);
    boolean existsByNameAndDeletedDateIsNull(String name);

}

