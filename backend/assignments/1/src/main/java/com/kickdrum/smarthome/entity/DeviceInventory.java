package com.kickdrum.smarthome.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "device_inventory")
public class DeviceInventory {

    @Id
    @Column(name = "kickston_id", nullable = false, length = 6)
    private String kickstonId;

    @Column(name = "device_username", nullable = false)
    private String deviceUsername;

    @Column(name = "device_password", nullable = false)
    private String devicePassword;

    @Column(name = "manufacture_date_time", nullable = false)
    private LocalDateTime manufactureDateTime;

    @Column(name = "manufacture_factory_place", nullable = false)
    private String manufactureFactoryPlace;

    protected DeviceInventory() {
        // JPA requirement
    }

    public String getKickstonId() {
        return kickstonId;
    }

    public String getDeviceUsername() {
        return deviceUsername;
    }

    public String getDevicePassword() {
        return devicePassword;
    }

    public LocalDateTime getManufactureDateTime() {
        return manufactureDateTime;
    }

    public String getManufactureFactoryPlace() {
        return manufactureFactoryPlace;
    }
}
