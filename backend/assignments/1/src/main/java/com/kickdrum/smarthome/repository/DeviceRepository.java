package com.kickdrum.smarthome.repository;

import com.kickdrum.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByKickstonIdAndDeletedDateIsNull(String kickstonId);

    Optional<Device> findByIdAndDeletedDateIsNull(Long id);

    List<Device> findAllByHouseIdAndDeletedDateIsNull(Long houseId);

    List<Device> findAllByRoomIdAndDeletedDateIsNull(Long roomId);
}
