package com.kickdrum.smarthome.controller.device;

import com.kickdrum.smarthome.dto.request.device.MoveDeviceRequest;
import com.kickdrum.smarthome.dto.response.ApiResponse;
import com.kickdrum.smarthome.entity.Device;
import com.kickdrum.smarthome.security.AuthenticatedUser;
import com.kickdrum.smarthome.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private static final Logger log =
            LoggerFactory.getLogger(DeviceController.class);

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ApiResponse<Device> registerDevice(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam Long houseId,
            @RequestParam String kickstonId,
            @RequestParam String deviceUsername,
            @RequestParam String devicePassword,
            @RequestParam(required = false) Long roomId
    ) {
        log.info("User {} attempting to register device {} in house {}",
                user.getUserId(), kickstonId, houseId);

        Device device =
                deviceService.registerDevice(
                        user.getUserId(),
                        houseId,
                        kickstonId,
                        deviceUsername,
                        devicePassword,
                        roomId
                );

        log.info("Device {} registered successfully in house {}",
                kickstonId, houseId);

        return ApiResponse.success(device);
    }
    @PostMapping("/{deviceId}/unassign")
    public ApiResponse<String> unassignDevice(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long deviceId
    ) {
        log.info("User {} unassigning device {}", user.getUserId(), deviceId);

        deviceService.unassignDeviceFromRoom(
                user.getUserId(),
                deviceId
        );

        return ApiResponse.success("Device unassigned from room");
    }
    @DeleteMapping("/{deviceId}")
    public ApiResponse<String> deleteDevice(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long deviceId
    ) {
        log.info("Admin {} deleting device {}", user.getUserId(), deviceId);

        deviceService.deleteDevice(
                user.getUserId(),
                deviceId
        );

        return ApiResponse.success("Device deleted successfully");
    }

    @PostMapping("/{deviceId}/move")
    public ApiResponse<String> moveDevice(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long deviceId,
            @RequestBody MoveDeviceRequest request
    ) {
        log.info("User {} attempting to move device {} to room {}",
                user.getUserId(), deviceId, request.getTargetRoomId());

        deviceService.moveDevice(
                user.getUserId(),
                deviceId,
                request.getTargetRoomId()
        );

        log.info("Device {} moved successfully", deviceId);

        return ApiResponse.success("Device moved successfully");
    }

    @GetMapping("/house/{houseId}")
    public ApiResponse<List<Device>> listDevicesByHouse(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long houseId
    ) {
        log.info("User {} listing devices for house {}",
                user.getUserId(), houseId);

        return ApiResponse.success(
                deviceService.listDevicesByHouse(
                        user.getUserId(),
                        houseId
                )
        );
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<Device>> listDevicesByRoom(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long roomId
    ) {
        log.info("User {} listing devices for room {}",
                user.getUserId(), roomId);

        return ApiResponse.success(
                deviceService.listDevicesByRoom(
                        user.getUserId(),
                        roomId
                )
        );
    }
}
