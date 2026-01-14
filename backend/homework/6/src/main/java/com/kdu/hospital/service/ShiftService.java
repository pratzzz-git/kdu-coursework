package com.kdu.hospital.service;

import com.kdu.hospital.entity.Shift;
import com.kdu.hospital.repo.ShiftRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    public List<Shift> getTop3Shifts() {

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 25);

        return shiftRepository.findShiftsByDateRange(
                startDate,
                endDate,
                PageRequest.of(0, 3)
        );
    }
}
