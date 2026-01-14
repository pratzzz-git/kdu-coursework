package com.kdu.hospital.controller;

import com.kdu.hospital.entity.ShiftType;
import com.kdu.hospital.service.ShiftTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shift-types")
public class ShiftTypeController {

    private final ShiftTypeService service;

    public ShiftTypeController(ShiftTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ShiftType save(@RequestBody ShiftType shiftType) {
        return service.save(shiftType);
    }
}
