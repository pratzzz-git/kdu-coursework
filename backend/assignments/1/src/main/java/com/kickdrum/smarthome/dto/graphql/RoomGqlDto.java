package com.kickdrum.smarthome.dto.graphql;

public class RoomGqlDto {

    private Long id;
    private String name;
    private Long houseId;

    public RoomGqlDto(Long id, String name, Long houseId) {
        this.id = id;
        this.name = name;
        this.houseId = houseId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getHouseId() {
        return houseId;
    }
}
