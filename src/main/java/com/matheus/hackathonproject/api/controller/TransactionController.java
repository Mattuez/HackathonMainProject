package com.matheus.hackathonproject.api.controller;

import com.matheus.hackathonproject.api.assembler.transaction.TransactionDtoAssembler;
import com.matheus.hackathonproject.api.assembler.transaction.TransactionDtoDisassembler;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionDto;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionInputDto;
import com.matheus.hackathonproject.core.security.AuthenticationSecurity;
import com.matheus.hackathonproject.domain.model.Transaction;
import com.matheus.hackathonproject.domain.model.User;
import com.matheus.hackathonproject.domain.service.TransactionRegistrationService;
import com.matheus.hackathonproject.domain.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionRegistrationService transactionRegistrationService;
    private UserRegistrationService userRegistrationService;
    private TransactionDtoAssembler transactionDtoAssembler;
    private TransactionDtoDisassembler transactionDtoDisassembler;
    private AuthenticationSecurity authenticationSecurity;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<TransactionDto> getAll() {
        Long userId = authenticationSecurity.getUserId();
        User user = userRegistrationService.search(userId);

        return transactionDtoAssembler.toDtoCollection(user.getTransactions());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public TransactionDto add(@RequestBody TransactionInputDto transactionInputDto) {
        Long userId = authenticationSecurity.getUserId();
        Transaction transaction = transactionDtoDisassembler.toEntityObject(transactionInputDto);

        User user = userRegistrationService.search(userId);
        transaction.setUser(user);

        return transactionDtoAssembler.toDto(transactionRegistrationService.insert(transaction));
    }
}
