package com.kickdrum.smarthome.dto.request.device;

public class MoveDeviceRequest {

    private Long targetRoomId;

    public Long getTargetRoomId() {
        return targetRoomId;
    }

    public void setTargetRoomId(Long targetRoomId) {
        this.targetRoomId = targetRoomId;
    }
}
