package com.oop.eventticketingsystem.eventTransaction.observer;

import com.oop.eventticketingsystem.model.Transaction;
import com.oop.eventticketingsystem.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class for handling database sinker for transaction
 */
@Component
public class DatabaseSinker implements DomainEventObserver<Transaction> {
    private final TransactionService transactionService;

    @Autowired
    public DatabaseSinker(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void onDomainEvent(Transaction domainObject) {
        transactionService.saveTransaction(domainObject);
    }
}
