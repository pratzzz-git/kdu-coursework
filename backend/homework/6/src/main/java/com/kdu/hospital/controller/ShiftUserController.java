package com.kdu.hospital.controller;

import com.kdu.hospital.entity.ShiftUser;
import com.kdu.hospital.repo.ShiftUserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shift-users")
public class ShiftUserController {

    private final ShiftUserRepository repository;

    public ShiftUserController(ShiftUserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ShiftUser save(@RequestBody ShiftUser shiftUser) {
        return repository.save(shiftUser);
    }
}
