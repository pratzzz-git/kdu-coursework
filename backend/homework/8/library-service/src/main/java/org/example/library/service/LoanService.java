package org.example.library.service;

import org.example.library.domain.entity.Loan;

import java.util.UUID;

public interface LoanService {

    Loan borrowBook(UUID bookId, UUID userId);

    Loan returnBook(UUID bookId, UUID userId);
}
