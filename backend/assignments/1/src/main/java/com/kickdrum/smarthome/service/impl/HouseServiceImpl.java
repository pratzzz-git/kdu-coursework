package com.kickdrum.smarthome.service.impl;
import java.time.LocalDateTime;

import com.kickdrum.smarthome.entity.House;
import com.kickdrum.smarthome.entity.HouseUser;
import com.kickdrum.smarthome.exception.BusinessException;
import com.kickdrum.smarthome.repository.HouseRepository;
import com.kickdrum.smarthome.repository.HouseUserRepository;
import com.kickdrum.smarthome.repository.UserRepository;
import com.kickdrum.smarthome.service.HouseService;
import com.kickdrum.smarthome.util.ErrorCode;
import com.kickdrum.smarthome.util.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HouseServiceImpl.class);

    private final HouseRepository houseRepository;
    private final HouseUserRepository houseUserRepository;
    private final UserRepository userRepository;

    public HouseServiceImpl(
            HouseRepository houseRepository,
            HouseUserRepository houseUserRepository,
            UserRepository userRepository
    ) {
        this.houseRepository = houseRepository;
        this.houseUserRepository = houseUserRepository;
        this.userRepository = userRepository;
    }

    @Override
    public House createHouse(Long creatorUserId, String name, String address) {

        log.info("Creating house '{}' by user {}", name, creatorUserId);

        House house = new House(name, address);
        houseRepository.save(house);

        HouseUser adminMapping =
                new HouseUser(house.getId(), creatorUserId, Role.ADMIN);

        houseUserRepository.save(adminMapping);

        log.info("House created with id {} and admin {}", house.getId(), creatorUserId);

        return house;
    }

    @Override
    public void addUserToHouse(Long adminUserId, Long houseId, Long targetUserId) {

        log.info("User {} attempting to add user {} to house {}", adminUserId, targetUserId, houseId);

        validateAdmin(adminUserId, houseId);

        userRepository.findByIdAndDeletedDateIsNull(targetUserId)
                .orElseThrow(() -> {
                    log.warn("Target user {} not found", targetUserId);
                    return new BusinessException(
                            ErrorCode.USER_NOT_FOUND,
                            "Target user does not exist"
                    );
                });

        if (houseUserRepository
                .existsByHouseIdAndUserIdAndDeletedDateIsNull(houseId, targetUserId)) {

            log.warn("User {} already belongs to house {}", targetUserId, houseId);

            throw new BusinessException(
                    ErrorCode.HOUSE_ACCESS_DENIED,
                    "User already belongs to the house"
            );
        }

        HouseUser mapping =
                new HouseUser(houseId, targetUserId, Role.USER);

        houseUserRepository.save(mapping);

        log.info("User {} added to house {}", targetUserId, houseId);
    }
    @Override
    public void renameHouse(Long adminUserId, Long houseId, String name) {

        validateAdmin(adminUserId, houseId);

        if (houseRepository.existsByNameAndDeletedDateIsNull(name)) {
            throw new BusinessException(
                    ErrorCode.HOUSE_ALREADY_EXISTS,
                    "House with this name already exists"
            );
        }

        House house =
                houseRepository
                        .findByIdAndDeletedDateIsNull(houseId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_NOT_FOUND,
                                "House not found"
                        ));

        house.rename(name);
    }

    @Override
    public void deleteHouse(Long adminUserId, Long houseId) {

        validateAdmin(adminUserId, houseId);

        House house =
                houseRepository
                        .findByIdAndDeletedDateIsNull(houseId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_NOT_FOUND,
                                "House not found"
                        ));

        house.setDeletedDate(LocalDateTime.now());
    }

    @Override
    public void transferAdmin(Long currentAdminUserId,
                              Long houseId,
                              Long newAdminUserId) {

        log.info("Transferring admin of house {} from {} to {}",
                houseId, currentAdminUserId, newAdminUserId);

        validateAdmin(currentAdminUserId, houseId);

        HouseUser currentAdmin =
                houseUserRepository
                        .findByHouseIdAndRoleAndDeletedDateIsNull(houseId, Role.ADMIN)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_NOT_FOUND,
                                "Admin not found"
                        ));

        HouseUser newAdmin =
                houseUserRepository
                        .findByHouseIdAndUserIdAndDeletedDateIsNull(houseId, newAdminUserId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_ACCESS_DENIED,
                                "New admin must be a house member"
                        ));

        currentAdmin.changeRole(Role.USER);
        newAdmin.changeRole(Role.ADMIN);

        log.info("Admin transfer completed for house {}", houseId);
    }

    @Override
    public void updateHouseAddress(Long adminUserId, Long houseId, String address) {

        log.info("User {} updating address for house {}", adminUserId, houseId);

        validateAdmin(adminUserId, houseId);

        House house =
                houseRepository
                        .findByIdAndDeletedDateIsNull(houseId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_NOT_FOUND,
                                "House not found"
                        ));

        house.updateAddress(address);

        log.info("House {} address updated", houseId);
    }

    @Override
    public List<House> listHousesForUser(Long userId) {

        log.info("Listing houses for user {}", userId);

        return houseUserRepository
                .findAllByUserIdAndDeletedDateIsNull(userId)
                .stream()
                .map(mapping ->
                        houseRepository
                                .findByIdAndDeletedDateIsNull(mapping.getHouseId())
                                .orElse(null)
                )
                .filter(house -> house != null)
                .collect(Collectors.toList());
    }

    private void validateAdmin(Long userId, Long houseId) {

        HouseUser mapping =
                houseUserRepository
                        .findByHouseIdAndUserIdAndDeletedDateIsNull(houseId, userId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.HOUSE_ACCESS_DENIED,
                                "User not part of this house"
                        ));

        if (mapping.getRole() != Role.ADMIN) {
            throw new BusinessException(
                    ErrorCode.HOUSE_ACCESS_DENIED,
                    "User is not admin of this house"
            );
        }
    }
}
