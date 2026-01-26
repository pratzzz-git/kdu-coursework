package com.kickdrum.smarthome.service.impl;

import com.kickdrum.smarthome.entity.Device;
import com.kickdrum.smarthome.entity.Room;
import com.kickdrum.smarthome.exception.BusinessException;
import com.kickdrum.smarthome.repository.DeviceInventoryRepository;
import com.kickdrum.smarthome.repository.DeviceRepository;
import com.kickdrum.smarthome.repository.HouseUserRepository;
import com.kickdrum.smarthome.repository.RoomRepository;
import com.kickdrum.smarthome.util.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceInventoryRepository deviceInventoryRepository;

    @Mock
    private HouseUserRepository houseUserRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void moveDevice_success() {
        Long userId = 1L;
        Long deviceId = 10L;
        Long houseId = 100L;
        Long targetRoomId = 200L;

        Device device = new Device("KICK123", houseId, null);
        Room room = new Room(houseId, "Bedroom");

        when(deviceRepository.findByIdAndDeletedDateIsNull(deviceId))
                .thenReturn(Optional.of(device));

        when(houseUserRepository.existsByHouseIdAndUserIdAndDeletedDateIsNull(houseId, userId))
                .thenReturn(true);

        when(roomRepository.findByIdAndDeletedDateIsNull(targetRoomId))
                .thenReturn(Optional.of(room));

        deviceService.moveDevice(userId, deviceId, targetRoomId);

        assertEquals(targetRoomId, device.getRoomId());
    }

    @Test
    void moveDevice_deviceNotFound() {
        when(deviceRepository.findByIdAndDeletedDateIsNull(10L))
                .thenReturn(Optional.empty());

        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> deviceService.moveDevice(1L, 10L, 20L)
        );

        assertEquals(ErrorCode.DEVICE_NOT_FOUND, ex.getErrorCode());
    }

    @Test
    void moveDevice_userNotInHouse() {
        Device device = new Device("KICK123", 100L, null);

        when(deviceRepository.findByIdAndDeletedDateIsNull(10L))
                .thenReturn(Optional.of(device));

        when(houseUserRepository.existsByHouseIdAndUserIdAndDeletedDateIsNull(100L, 1L))
                .thenReturn(false);

        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> deviceService.moveDevice(1L, 10L, 20L)
        );

        assertEquals(ErrorCode.HOUSE_ACCESS_DENIED, ex.getErrorCode());
    }

    @Test
    void moveDevice_roomFromAnotherHouse() {
        Device device = new Device("KICK123", 100L, null);
        Room room = new Room(999L, "Other Room");

        when(deviceRepository.findByIdAndDeletedDateIsNull(10L))
                .thenReturn(Optional.of(device));

        when(houseUserRepository.existsByHouseIdAndUserIdAndDeletedDateIsNull(100L, 1L))
                .thenReturn(true);

        when(roomRepository.findByIdAndDeletedDateIsNull(20L))
                .thenReturn(Optional.of(room));

        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> deviceService.moveDevice(1L, 10L, 20L)
        );

        assertEquals(ErrorCode.ROOM_ACCESS_DENIED, ex.getErrorCode());
    }
}
