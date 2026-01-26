package com.kickdrum.smarthome.controller.house;

import com.kickdrum.smarthome.dto.request.house.CreateHouseRequest;
import com.kickdrum.smarthome.dto.response.ApiResponse;
import com.kickdrum.smarthome.dto.response.house.HouseResponse;
import com.kickdrum.smarthome.entity.House;
import com.kickdrum.smarthome.exception.BusinessException;
import com.kickdrum.smarthome.security.AuthenticatedUser;
import com.kickdrum.smarthome.service.HouseService;
import com.kickdrum.smarthome.util.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HouseController.class);

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ApiResponse<HouseResponse> createHouse(
            Authentication authentication,
            @RequestBody CreateHouseRequest request
    ) {
        AuthenticatedUser user = extractUser(authentication);

        log.info("User {} creating house with name '{}'",
                user.getUserId(), request.getName());

        House house =
                houseService.createHouse(
                        user.getUserId(),
                        request.getName(),
                        request.getAddress()
                );

        return ApiResponse.success(toResponse(house));
    }

    @GetMapping
    public ApiResponse<List<HouseResponse>> listHouses(
            Authentication authentication
    ) {
        AuthenticatedUser user = extractUser(authentication);

        log.info("Listing houses for user {}", user.getUserId());

        List<HouseResponse> houses =
                houseService
                        .listHousesForUser(user.getUserId())
                        .stream()
                        .map(this::toResponse)
                        .collect(Collectors.toList());

        return ApiResponse.success(houses);
    }

    @PostMapping("/{houseId}/users/{userId}")
    public ApiResponse<String> addUserToHouse(
            Authentication authentication,
            @PathVariable Long houseId,
            @PathVariable Long userId
    ) {
        AuthenticatedUser admin = extractUser(authentication);

        log.info("Admin {} adding user {} to house {}",
                admin.getUserId(), userId, houseId);

        houseService.addUserToHouse(
                admin.getUserId(),
                houseId,
                userId
        );

        return ApiResponse.success("User added to house successfully");
    }

    @PostMapping("/{houseId}/transfer-admin/{newAdminUserId}")
    public ApiResponse<String> transferAdmin(
            Authentication authentication,
            @PathVariable Long houseId,
            @PathVariable Long newAdminUserId
    ) {
        AuthenticatedUser admin = extractUser(authentication);

        log.info("Admin {} transferring admin role of house {} to {}",
                admin.getUserId(), houseId, newAdminUserId);

        houseService.transferAdmin(
                admin.getUserId(),
                houseId,
                newAdminUserId
        );

        return ApiResponse.success("Admin role transferred successfully");
    }

    @PutMapping("/{houseId}/address")
    public ApiResponse<String> updateHouseAddress(
            Authentication authentication,
            @PathVariable Long houseId,
            @RequestBody CreateHouseRequest request
    ) {
        AuthenticatedUser admin = extractUser(authentication);

        log.info("Admin {} updating address for house {}",
                admin.getUserId(), houseId);

        houseService.updateHouseAddress(
                admin.getUserId(),
                houseId,
                request.getAddress()
        );

        return ApiResponse.success("House address updated successfully");
    }
    @PutMapping("/{houseId}/name")
    public ApiResponse<String> renameHouse(
            Authentication authentication,
            @PathVariable Long houseId,
            @RequestBody CreateHouseRequest request
    ) {
        AuthenticatedUser admin = extractUser(authentication);

        log.info("Admin {} renaming house {} to '{}'",
                admin.getUserId(), houseId, request.getName());

        houseService.renameHouse(
                admin.getUserId(),
                houseId,
                request.getName()
        );

        return ApiResponse.success("House renamed successfully");
    }

    @DeleteMapping("/{houseId}")
    public ApiResponse<String> deleteHouse(
            Authentication authentication,
            @PathVariable Long houseId
    ) {
        AuthenticatedUser admin = extractUser(authentication);

        log.info("Admin {} deleting house {}",
                admin.getUserId(), houseId);

        houseService.deleteHouse(
                admin.getUserId(),
                houseId
        );

        return ApiResponse.success("House deleted successfully");
    }


    private HouseResponse toResponse(House house) {
        return new HouseResponse(
                house.getId(),
                house.getName(),
                house.getAddress()
        );
    }

    private AuthenticatedUser extractUser(Authentication authentication) {
        if (authentication == null ||
                !(authentication.getPrincipal() instanceof AuthenticatedUser)) {

            log.warn("Authentication failed: invalid or missing JWT");

            throw new BusinessException(
                    ErrorCode.AUTH_FAILED,
                    "Invalid or missing JWT token"
            );
        }
        return (AuthenticatedUser) authentication.getPrincipal();
    }
}
