package com.kdu.hospital.controller;

import com.kdu.hospital.entity.Shift;
import com.kdu.hospital.service.ShiftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    private final ShiftService service;

    public ShiftController(ShiftService service) {
        this.service = service;
    }

    @PostMapping
    public Shift save(@RequestBody Shift shift) {
        return service.save(shift);
    }

    @GetMapping("/top")
    public List<Shift> getTop3Shifts() {
        return service.getTop3Shifts();
    }
}
