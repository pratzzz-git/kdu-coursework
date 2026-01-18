package org.example.library.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.example.library.domain.entity.Book;
import org.example.library.domain.entity.Loan;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.repository.BookRepository;
import org.example.library.domain.repository.LoanRepository;
import org.example.library.service.LoanService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class LoanServiceImpl implements LoanService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public LoanServiceImpl(BookRepository bookRepository,
                           LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan borrowBook(UUID bookId) {
        log.info("Borrow request | bookId={}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book not found"));

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Book is not available for borrowing");
        }

        book.setStatus(BookStatus.BORROWED);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBorrowedAt(Instant.now());

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnBook(UUID loanId) {
        log.info("Return request | loanId={}", loanId);

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalStateException("Loan not found"));

        loan.setReturnedAt(Instant.now());
        loan.getBook().setStatus(BookStatus.AVAILABLE);

        return loan;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> getActiveLoans() {
        log.info("Fetching active loans");

        return loanRepository.findAll()
                .stream()
                .filter(l -> l.getReturnedAt() == null)
                .toList();
    }
}
