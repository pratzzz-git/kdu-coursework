package com.kickdrum.smarthome.repository;

import com.kickdrum.smarthome.entity.HouseUser;
import com.kickdrum.smarthome.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseUserRepository extends JpaRepository<HouseUser, Long> {

    Optional<HouseUser> findByHouseIdAndUserIdAndDeletedDateIsNull(
            Long houseId,
            Long userId
    );

    Optional<HouseUser> findByHouseIdAndRoleAndDeletedDateIsNull(
            Long houseId,
            Role role
    );

    List<HouseUser> findAllByUserIdAndDeletedDateIsNull(Long userId);

    boolean existsByHouseIdAndUserIdAndDeletedDateIsNull(
            Long houseId,
            Long userId
    );
}
