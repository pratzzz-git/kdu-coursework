package com.kickdrum.smarthome.controller.room;

import com.kickdrum.smarthome.dto.response.ApiResponse;
import com.kickdrum.smarthome.entity.Room;
import com.kickdrum.smarthome.security.AuthenticatedUser;
import com.kickdrum.smarthome.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private static final Logger log =
            LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ApiResponse<Room> createRoom(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam Long houseId,
            @RequestParam String roomName
    ) {
        log.info("User {} creating room '{}' in house {}",
                user.getUserId(), roomName, houseId);

        return ApiResponse.success(
                roomService.createRoom(user.getUserId(), houseId, roomName)
        );
    }

    @GetMapping("/house/{houseId}")
    public ApiResponse<List<Room>> listRooms(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long houseId
    ) {
        log.info("User {} listing rooms for house {}",
                user.getUserId(), houseId);

        return ApiResponse.success(
                roomService.listRooms(user.getUserId(), houseId)
        );
    }

    @PutMapping("/{roomId}/rename")
    public ApiResponse<String> renameRoom(
            @AuthenticationPrincipal AuthenticatedUser admin,
            @PathVariable Long roomId,
            @RequestParam String name
    ) {
        roomService.renameRoom(admin.getUserId(), roomId, name);
        return ApiResponse.success("Room renamed successfully");
    }

    @DeleteMapping("/{roomId}")
    public ApiResponse<String> deleteRoom(
            @AuthenticationPrincipal AuthenticatedUser admin,
            @PathVariable Long roomId
    ) {
        roomService.deleteRoom(admin.getUserId(), roomId);
        return ApiResponse.success("Room deleted successfully");
    }
}
