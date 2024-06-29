package com.matheus.hackathonproject.api.model.userTransaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {

    private Long id;
    private Long value;
    private String userCpf;
}
