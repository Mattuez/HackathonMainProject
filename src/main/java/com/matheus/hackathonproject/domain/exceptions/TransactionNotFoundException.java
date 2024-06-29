package com.matheus.hackathonproject.domain.exceptions;

public class TransactionNotFoundException extends EntityNotFoundException{
    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(Long transactionId) {
        this(String.format("Transaction with id %d doesn't exist", transactionId));
    }

    public TransactionNotFoundException(Long transactionId, Long userId) {
        this(String.format("User with id  %d dind't make any transaction with id %d", userId, transactionId));
    }
}
