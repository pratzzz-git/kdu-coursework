package com.kickdrum.smarthome.repository;

import com.kickdrum.smarthome.entity.DeviceInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceInventoryRepository
        extends JpaRepository<DeviceInventory, String> {
}
