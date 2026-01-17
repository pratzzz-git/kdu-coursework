package org.example.library.web.controller;

import org.example.library.api.dto.response.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<LoanResponse> borrowBook(@PathVariable UUID bookId) {
        return ResponseEntity.status(501).build(); // not implemented yet
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<LoanResponse> returnBook(@PathVariable UUID bookId) {
        return ResponseEntity.status(501).build();
    }
}
