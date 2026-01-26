package com.kickdrum.smarthome.controller.graphql;

import com.kickdrum.smarthome.dto.graphql.DeviceGqlDto;
import com.kickdrum.smarthome.dto.graphql.HouseGqlDto;
import com.kickdrum.smarthome.dto.graphql.RoomGqlDto;
import com.kickdrum.smarthome.repository.DeviceRepository;
import com.kickdrum.smarthome.repository.HouseRepository;
import com.kickdrum.smarthome.repository.RoomRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SmartHomeGraphQLController {

    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;

    public SmartHomeGraphQLController(
            HouseRepository houseRepository,
            RoomRepository roomRepository,
            DeviceRepository deviceRepository
    ) {
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    @QueryMapping
    public List<HouseGqlDto> houses() {
        return houseRepository.findAll()
                .stream()
                .map(h -> new HouseGqlDto(
                        h.getId(),
                        h.getName(),
                        h.getAddress()
                ))
                .toList();
    }

    @QueryMapping
    public List<RoomGqlDto> roomsByHouse(@Argument Long houseId) {
        return roomRepository.findAllByHouseIdAndDeletedDateIsNull(houseId)
                .stream()
                .map(r -> new RoomGqlDto(
                        r.getId(),
                        r.getName(),
                        r.getHouseId()
                ))
                .toList();
    }

    @QueryMapping
    public List<DeviceGqlDto> devicesByHouse(@Argument Long houseId) {
        return deviceRepository.findAllByHouseIdAndDeletedDateIsNull(houseId)
                .stream()
                .map(d -> new DeviceGqlDto(
                        d.getId(),
                        d.getKickstonId(),
                        d.getHouseId(),
                        d.getRoomId()
                ))
                .toList();
    }

    @QueryMapping
    public List<DeviceGqlDto> devicesByRoom(@Argument Long roomId) {
        return deviceRepository.findAllByRoomIdAndDeletedDateIsNull(roomId)
                .stream()
                .map(d -> new DeviceGqlDto(
                        d.getId(),
                        d.getKickstonId(),
                        d.getHouseId(),
                        d.getRoomId()
                ))
                .toList();
    }
}
