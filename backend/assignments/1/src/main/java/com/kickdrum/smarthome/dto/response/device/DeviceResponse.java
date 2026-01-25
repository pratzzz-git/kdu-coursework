package com.kickdrum.smarthome.dto.response.device;

public class DeviceResponse {

    private Long id;
    private String kickstonId;
    private Long roomId;

    public DeviceResponse(Long id, String kickstonId, Long roomId) {
        this.id = id;
        this.kickstonId = kickstonId;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public String getKickstonId() {
        return kickstonId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
