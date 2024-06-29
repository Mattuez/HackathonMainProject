package com.matheus.hackathonproject.api.controller;

import com.matheus.hackathonproject.api.assembler.transaction.TransactionDtoAssembler;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionDto;
import com.matheus.hackathonproject.core.security.CheckSecurity;
import com.matheus.hackathonproject.domain.model.Transaction;
import com.matheus.hackathonproject.domain.model.User;
import com.matheus.hackathonproject.domain.service.TransactionRegistrationService;
import com.matheus.hackathonproject.domain.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("users/{userId}/transactions")
public class UserTransactionController {

    private UserRegistrationService userService;
    private TransactionRegistrationService transactionRegistrationService;
    private TransactionDtoAssembler transactionDtoAssembler;

    @CheckSecurity.Transaction.canSearch
    @GetMapping
    public List<TransactionDto> getAllFromUser(@PathVariable("userId") Long userId) {
        User user = userService.search(userId);

        return transactionDtoAssembler.toDtoCollection(user.getTransactions());
    }

    @CheckSecurity.Transaction.canSearch
    @GetMapping("/{transactionId}")
    public TransactionDto getById(@PathVariable("userId") Long userId,
                                  @PathVariable("transactionId") Long transactionId) {
        Transaction transaction = transactionRegistrationService.search(transactionId, userId);

        return transactionDtoAssembler.toDto(transaction);
    }
}
