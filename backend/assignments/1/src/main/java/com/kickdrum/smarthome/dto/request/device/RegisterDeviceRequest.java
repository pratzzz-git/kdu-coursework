package com.kickdrum.smarthome.dto.request.device;

public class RegisterDeviceRequest {

    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private Long roomId;

    public String getKickstonId() {
        return kickstonId;
    }

    public String getDeviceUsername() {
        return deviceUsername;
    }

    public String getDevicePassword() {
        return devicePassword;
    }

    public Long getRoomId() {
        return roomId;
    }
}
