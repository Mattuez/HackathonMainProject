package com.matheus.hackathonproject.domain.repository;

import com.matheus.hackathonproject.domain.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CustomJpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdAndUserId(Long id, Long userId);
}
