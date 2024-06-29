package com.matheus.hackathonproject.domain.service;

import com.matheus.hackathonproject.domain.exceptions.TransactionNotFoundException;
import com.matheus.hackathonproject.domain.model.Transaction;
import com.matheus.hackathonproject.domain.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionRegistrationService {

    private TransactionRepository transactionRepository;

    public List<Transaction> searchAll() {
        return transactionRepository.findAll();
    }

    public Transaction search(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }

    public Transaction search(Long transactionId, Long userId) {
        return transactionRepository.findByIdAndUserId(transactionId, userId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId, userId));
    }

    @Transactional
    public Transaction insert(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}