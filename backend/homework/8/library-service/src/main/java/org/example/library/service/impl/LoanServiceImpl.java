package org.example.library.service.impl;

import org.example.library.domain.entity.Book;
import org.example.library.domain.entity.Loan;
import org.example.library.domain.enums.BookStatus;
import org.example.library.domain.entity.User;
import org.example.library.domain.repository.BookRepository;
import org.example.library.domain.repository.LoanRepository;
import org.example.library.domain.repository.UserRepository;
import org.example.library.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public LoanServiceImpl(BookRepository bookRepository,
                           LoanRepository loanRepository,
                           UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Loan borrowBook(UUID bookId, UUID userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book not found"));

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("BOOK_NOT_AVAILABLE");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBorrower(user);
        loan.setBorrowedAt(Instant.now());

        book.setStatus(BookStatus.CHECKED_OUT);

        // IMPORTANT: save loan, but book update happens in same transaction
        loanRepository.save(loan);

        return loan;
    }


    @Override
    public Loan returnBook(UUID bookId, UUID userId) {
        Loan loan = loanRepository.findByBookIdAndReturnedAtIsNull(bookId)
                .orElseThrow(() -> new IllegalStateException("No active loan"));

        loan.setReturnedAt(Instant.now());
        loan.getBook().setStatus(BookStatus.AVAILABLE);

        return loan;
    }
}
