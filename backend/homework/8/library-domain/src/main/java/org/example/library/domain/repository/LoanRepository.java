package org.example.library.domain.repository;

import org.example.library.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    Optional<Loan> findByBookIdAndReturnedAtIsNull(UUID bookId);
}
