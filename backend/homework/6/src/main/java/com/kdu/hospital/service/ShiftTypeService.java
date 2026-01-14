package com.kdu.hospital.service;

import com.kdu.hospital.entity.ShiftType;
import com.kdu.hospital.repo.ShiftTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ShiftTypeService {

    private final ShiftTypeRepository repository;

    public ShiftTypeService(ShiftTypeRepository repository) {
        this.repository = repository;
    }

    public ShiftType save(ShiftType shiftType) {
        return repository.save(shiftType);
    }
}
