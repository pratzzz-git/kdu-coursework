package org.example.library.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.library.api.dto.response.LoanResponse;
import org.example.library.service.LoanService;
import org.example.library.web.mapper.LoanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loans")
@Slf4j
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // BORROW BOOK
    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<LoanResponse> borrowBook(
            @PathVariable(name = "bookId") UUID bookId
    ) {
        log.info("POST /loans/borrow/{}", bookId);

        LoanResponse response =
                LoanMapper.toResponse(
                        loanService.borrowBook(bookId)
                );

        return ResponseEntity.ok(response);
    }

    // RETURN BOOK
    @PostMapping("/return/{loanId}")
    public ResponseEntity<LoanResponse> returnBook(
            @PathVariable(name = "loanId") UUID loanId
    ) {
        log.info("POST /loans/return/{}", loanId);

        LoanResponse response =
                LoanMapper.toResponse(
                        loanService.returnBook(loanId)
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getActiveLoans() {
        log.info("GET /loans");

        List<LoanResponse> response =
                loanService.getActiveLoans()
                        .stream()
                        .map(LoanMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

}
