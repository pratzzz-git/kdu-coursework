package com.kickdrum.smarthome.repository;

import com.kickdrum.smarthome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByHouseIdAndDeletedDateIsNull(Long houseId);

    Optional<Room> findByIdAndDeletedDateIsNull(Long id);

    boolean existsByHouseIdAndNameAndDeletedDateIsNull(Long houseId, String name);
}
