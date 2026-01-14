package com.kdu.hospital.repo;

import com.kdu.hospital.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShiftTypeRepository extends JpaRepository<ShiftType, UUID> {
}
