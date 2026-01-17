package org.example.library.web.controller;

import org.example.library.api.dto.response.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Loans", description = "Book borrowing APIs")
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Operation(summary = "Borrow a book")
    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<LoanResponse> borrowBook(@PathVariable UUID bookId) {
        return ResponseEntity.status(501).build(); // not implemented yet
    }

    @Operation(summary = "Return a book")
    @PostMapping("/{bookId}/return")
    public ResponseEntity<LoanResponse> returnBook(@PathVariable UUID bookId) {
        return ResponseEntity.status(501).build();
    }
}
