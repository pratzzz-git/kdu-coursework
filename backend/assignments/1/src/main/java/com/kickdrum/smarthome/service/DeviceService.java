package com.kickdrum.smarthome.service;

import com.kickdrum.smarthome.entity.Device;

import java.util.List;

public interface DeviceService {

    Device registerDevice(
            Long adminUserId,
            Long houseId,
            String kickstonId,
            String deviceUsername,
            String devicePassword,
            Long roomId
    );

    void moveDevice(
            Long userId,
            Long deviceId,
            Long targetRoomId
    );

    List<Device> listDevicesByHouse(Long userId, Long houseId);

    List<Device> listDevicesByRoom(Long userId, Long roomId);
    void unassignDeviceFromRoom(Long userId, Long deviceId);

    void deleteDevice(Long adminUserId, Long deviceId);
}
