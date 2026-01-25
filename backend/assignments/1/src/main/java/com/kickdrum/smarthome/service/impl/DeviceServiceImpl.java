package com.kickdrum.smarthome.service.impl;

import com.kickdrum.smarthome.entity.Device;
import com.kickdrum.smarthome.entity.DeviceInventory;
import com.kickdrum.smarthome.entity.HouseUser;
import com.kickdrum.smarthome.entity.Room;
import com.kickdrum.smarthome.exception.BusinessException;
import com.kickdrum.smarthome.repository.DeviceInventoryRepository;
import com.kickdrum.smarthome.repository.DeviceRepository;
import com.kickdrum.smarthome.repository.HouseUserRepository;
import com.kickdrum.smarthome.repository.RoomRepository;
import com.kickdrum.smarthome.service.DeviceService;
import com.kickdrum.smarthome.util.ErrorCode;
import com.kickdrum.smarthome.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private static final Logger log =
            LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;
    private final DeviceInventoryRepository deviceInventoryRepository;
    private final HouseUserRepository houseUserRepository;
    private final RoomRepository roomRepository;

    public DeviceServiceImpl(
            DeviceRepository deviceRepository,
            DeviceInventoryRepository deviceInventoryRepository,
            HouseUserRepository houseUserRepository,
            RoomRepository roomRepository
    ) {
        this.deviceRepository = deviceRepository;
        this.deviceInventoryRepository = deviceInventoryRepository;
        this.houseUserRepository = houseUserRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Device registerDevice(
            Long adminUserId,
            Long houseId,
            String kickstonId,
            String deviceUsername,
            String devicePassword,
            Long roomId
    ) {

        log.info("Validating admin {} for house {}", adminUserId, houseId);
        validateAdmin(adminUserId, houseId);

        DeviceInventory inventory =
                deviceInventoryRepository
                        .findById(kickstonId)
                        .orElseThrow(() -> {
                            log.warn("Device {} not found in inventory", kickstonId);
                            return new BusinessException(
                                    ErrorCode.DEVICE_NOT_FOUND,
                                    "Device not found in inventory"
                            );
                        });

        if (!inventory.getDeviceUsername().equals(deviceUsername)
                || !inventory.getDevicePassword().equals(devicePassword)) {

            log.warn("Invalid credentials for device {}", kickstonId);

            throw new BusinessException(
                    ErrorCode.DEVICE_INVALID_CREDENTIALS,
                    "Invalid device credentials"
            );
        }

        deviceRepository
                .findByKickstonIdAndDeletedDateIsNull(kickstonId)
                .ifPresent(d -> {
                    log.warn("Device {} already registered", kickstonId);
                    throw new BusinessException(
                            ErrorCode.DEVICE_ALREADY_REGISTERED,
                            "Device already registered"
                    );
                });

        if (roomId != null) {
            log.info("Validating room {} belongs to house {}", roomId, houseId);
            validateRoomBelongsToHouse(roomId, houseId);
        }

        Device device = new Device(kickstonId, houseId, roomId);

        Device saved = deviceRepository.save(device);

        log.info("Device {} successfully saved with id {}", kickstonId, saved.getId());

        return saved;
    }

    @Override
    public void deleteDevice(Long adminUserId, Long deviceId) {

        log.info("Admin {} deleting device {}", adminUserId, deviceId);

        Device device =
                deviceRepository
                        .findByIdAndDeletedDateIsNull(deviceId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.DEVICE_NOT_FOUND,
                                "Device not found"
                        ));

        validateAdmin(adminUserId, device.getHouseId());

        device.setDeletedDate(java.time.LocalDateTime.now());

        log.info("Device {} soft deleted", deviceId);
    }

    @Override
    public void unassignDeviceFromRoom(Long userId, Long deviceId) {

        log.info("User {} unassigning device {} from room", userId, deviceId);

        Device device =
                deviceRepository
                        .findByIdAndDeletedDateIsNull(deviceId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.DEVICE_NOT_FOUND,
                                "Device not found"
                        ));

        validateHouseMembership(userId, device.getHouseId());

        device.moveToRoom(null);

        log.info("Device {} unassigned from room", deviceId);
    }

    @Override
    public void moveDevice(Long userId, Long deviceId, Long targetRoomId) {

        log.info("User {} moving device {} to room {}", userId, deviceId, targetRoomId);

        Device device =
                deviceRepository
                        .findByIdAndDeletedDateIsNull(deviceId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.DEVICE_NOT_FOUND,
                                "Device not found"
                        ));

        validateHouseMembership(userId, device.getHouseId());
        validateRoomBelongsToHouse(targetRoomId, device.getHouseId());

        device.moveToRoom(targetRoomId);

        log.info("Device {} moved successfully", deviceId);
    }

    @Override
    public List<Device> listDevicesByHouse(Long userId, Long houseId) {

        log.info("User {} listing devices for house {}", userId, houseId);

        validateHouseMembership(userId, houseId);

        return deviceRepository.findAllByHouseIdAndDeletedDateIsNull(houseId);
    }

    @Override
    public List<Device> listDevicesByRoom(Long userId, Long roomId) {

        Room room =
                roomRepository
                        .findByIdAndDeletedDateIsNull(roomId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.ROOM_NOT_FOUND,
                                "Room not found"
                        ));

        validateHouseMembership(userId, room.getHouseId());

        log.info("User {} listing devices for room {}", userId, roomId);

        return deviceRepository.findAllByRoomIdAndDeletedDateIsNull(roomId);
    }

    private void validateAdmin(Long userId, Long houseId) {

        HouseUser mapping =
                houseUserRepository
                        .findByHouseIdAndUserIdAndDeletedDateIsNull(houseId, userId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_ACCESS_DENIED,
                                "User not part of this house"
                        ));

        if (mapping.getRole() != Role.ADMIN) {
            throw new BusinessException(
                    ErrorCode.HOUSE_ACCESS_DENIED,
                    "Only admin can perform this action"
            );
        }
    }

    private void validateHouseMembership(Long userId, Long houseId) {

        boolean exists =
                houseUserRepository
                        .existsByHouseIdAndUserIdAndDeletedDateIsNull(houseId, userId);

        if (!exists) {
            throw new BusinessException(
                    ErrorCode.HOUSE_ACCESS_DENIED,
                    "User not part of this house"
            );
        }
    }

    private void validateRoomBelongsToHouse(Long roomId, Long houseId) {

        Room room =
                roomRepository
                        .findByIdAndDeletedDateIsNull(roomId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.ROOM_NOT_FOUND,
                                "Room not found"
                        ));

        if (!room.getHouseId().equals(houseId)) {
            throw new BusinessException(
                    ErrorCode.ROOM_ACCESS_DENIED,
                    "Room does not belong to this house"
            );
        }
    }
}
