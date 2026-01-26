package com.kickdrum.smarthome.dto.graphql;

public class DeviceGqlDto {

    private Long id;
    private String kickstonId;
    private Long houseId;
    private Long roomId;

    public DeviceGqlDto(Long id, String kickstonId, Long houseId, Long roomId) {
        this.id = id;
        this.kickstonId = kickstonId;
        this.houseId = houseId;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public String getKickstonId() {
        return kickstonId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
