package com.kickdrum.smarthome.service;

import com.kickdrum.smarthome.entity.House;

import java.util.List;

public interface HouseService {

    House createHouse(Long creatorUserId, String name, String address);

    void addUserToHouse(Long adminUserId, Long houseId, Long targetUserId);

    void transferAdmin(Long currentAdminUserId, Long houseId, Long newAdminUserId);

    void updateHouseAddress(Long adminUserId, Long houseId, String address);

    void renameHouse(Long adminUserId, Long houseId, String name);

    void deleteHouse(Long adminUserId, Long houseId);

    List<House> listHousesForUser(Long userId);
}
