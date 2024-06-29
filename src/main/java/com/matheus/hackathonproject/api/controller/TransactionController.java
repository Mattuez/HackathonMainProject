package com.matheus.hackathonproject.api.controller;

import com.matheus.hackathonproject.api.assembler.transaction.TransactionDtoAssembler;
import com.matheus.hackathonproject.api.assembler.transaction.TransactionDtoDisassembler;
import com.matheus.hackathonproject.api.assembler.transaction.TransactionExternModel;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionDto;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionInputDto;
import com.matheus.hackathonproject.core.security.AuthenticationSecurity;
import com.matheus.hackathonproject.core.security.CheckSecurity;
import com.matheus.hackathonproject.domain.model.Transaction;
import com.matheus.hackathonproject.domain.model.User;
import com.matheus.hackathonproject.domain.service.ApiExternaService;
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
    private ApiExternaService apiExternaService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<TransactionDto> getAll() {
        Long userId = authenticationSecurity.getUserId();
        User user = userRegistrationService.search(userId);

        return transactionDtoAssembler.toDtoCollection(user.getTransactions());
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{transactionId}")
    public TransactionDto getById(@PathVariable("userId") Long userId,
                                  @PathVariable("transactionId") Long transactionId) {
        Transaction transaction = transactionRegistrationService.search(transactionId, userId);

        return transactionDtoAssembler.toDto(transaction);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public TransactionDto add(@RequestBody TransactionInputDto transactionInputDto) {
        Long userId = authenticationSecurity.getUserId();
        Transaction transaction = transactionDtoDisassembler.toEntityObject(transactionInputDto);

        User user = userRegistrationService.search(userId);
        transaction.setUser(user);

        TransactionExternModel externModel = TransactionExternModel.builder()
                .cpf(user.getCpf())
                .app_name("AplicaçãoMatheus_e_Felipe")
                .valor(transaction.getValue())
                .build();

        apiExternaService.enviarDadosParaApiExterna(externModel);

        return transactionDtoAssembler.toDto(transactionRegistrationService.insert(transaction));
    }
}
