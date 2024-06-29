package com.matheus.hackathonproject.api.assembler.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TransactionExternModel {

    private String cpf;
    private String app_name;
    private Long valor;
}
