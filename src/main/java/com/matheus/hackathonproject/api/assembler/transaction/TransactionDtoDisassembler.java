package com.matheus.hackathonproject.api.assembler.transaction;

import com.matheus.hackathonproject.api.model.accessLevel.AccessLevelInputDto;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionInputDto;
import com.matheus.hackathonproject.domain.model.AccessLevel;
import com.matheus.hackathonproject.domain.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoDisassembler {
    private ModelMapper modelMapper;

    public TransactionDtoDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transaction toEntityObject(TransactionInputDto source) {
        return modelMapper.map(source, Transaction.class);
    }

    public void copyToEntityObject(TransactionInputDto source, Transaction destination) {
        modelMapper.map(source, destination);
    }
}
