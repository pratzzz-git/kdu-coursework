package com.kickdrum.smarthome.service.impl;

import com.kickdrum.smarthome.entity.HouseUser;
import com.kickdrum.smarthome.entity.Room;
import com.kickdrum.smarthome.exception.BusinessException;
import com.kickdrum.smarthome.repository.HouseRepository;
import com.kickdrum.smarthome.repository.HouseUserRepository;
import com.kickdrum.smarthome.repository.RoomRepository;
import com.kickdrum.smarthome.service.RoomService;
import com.kickdrum.smarthome.util.ErrorCode;
import com.kickdrum.smarthome.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private static final Logger log =
            LoggerFactory.getLogger(RoomServiceImpl.class);

    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final HouseUserRepository houseUserRepository;

    public RoomServiceImpl(
            RoomRepository roomRepository,
            HouseRepository houseRepository,
            HouseUserRepository houseUserRepository
    ) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.houseUserRepository = houseUserRepository;
    }

    @Override
    public Room createRoom(Long adminUserId, Long houseId, String roomName) {

        log.info("Admin {} creating room '{}' in house {}",
                adminUserId, roomName, houseId);

        validateAdmin(adminUserId, houseId);

        houseRepository
                .findByIdAndDeletedDateIsNull(houseId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.HOUSE_NOT_FOUND,
                        "House not found"
                ));

        Room room = new Room(houseId, roomName);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> listRooms(Long userId, Long houseId) {

        log.info("User {} listing rooms for house {}", userId, houseId);

        validateHouseMembership(userId, houseId);

        return roomRepository.findAllByHouseIdAndDeletedDateIsNull(houseId);
    }

    @Override
    public void renameRoom(Long adminUserId, Long roomId, String newName) {

        Room room =
                roomRepository
                        .findByIdAndDeletedDateIsNull(roomId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.ROOM_NOT_FOUND,
                                "Room not found"
                        ));

        validateAdmin(adminUserId, room.getHouseId());

        log.info("Admin {} renaming room {} to '{}'",
                adminUserId, roomId, newName);

        room.rename(newName);
    }

    @Override
    public void deleteRoom(Long adminUserId, Long roomId) {

        Room room =
                roomRepository
                        .findByIdAndDeletedDateIsNull(roomId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.ROOM_NOT_FOUND,
                                "Room not found"
                        ));

        validateAdmin(adminUserId, room.getHouseId());

        log.info("Admin {} deleting room {}", adminUserId, roomId);

        room.setDeletedDate(LocalDateTime.now());
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
                    ErrorCode.ROOM_ACCESS_DENIED,
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
}
