package org.example.library.service;

import org.example.library.domain.entity.Loan;

import java.util.List;
import java.util.UUID;

public interface LoanService {

    Loan borrowBook(UUID bookId);

    Loan returnBook(UUID loanId);

    List<Loan> getActiveLoans();
}
