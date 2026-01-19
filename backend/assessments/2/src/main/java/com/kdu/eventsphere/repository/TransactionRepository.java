package com.kdu.eventsphere.repository;

import com.kdu.eventsphere.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
