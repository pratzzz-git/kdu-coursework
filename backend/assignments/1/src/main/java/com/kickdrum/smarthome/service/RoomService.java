package com.kickdrum.smarthome.service;

import com.kickdrum.smarthome.entity.Room;

import java.util.List;

public interface RoomService {

    Room createRoom(Long adminUserId, Long houseId, String roomName);

    List<Room> listRooms(Long userId, Long houseId);

    void renameRoom(Long adminUserId, Long roomId, String newName);

    void deleteRoom(Long adminUserId, Long roomId);
}
