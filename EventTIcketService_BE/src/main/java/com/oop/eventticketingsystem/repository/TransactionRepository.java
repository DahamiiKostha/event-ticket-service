package com.oop.eventticketingsystem.repository;

import com.oop.eventticketingsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A class for TransactionRepository
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
