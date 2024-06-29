package com.matheus.hackathonproject.api.assembler.transaction;

import com.matheus.hackathonproject.api.model.accessLevel.AccessLevelDto;
import com.matheus.hackathonproject.api.model.userTransaction.TransactionDto;
import com.matheus.hackathonproject.domain.model.AccessLevel;
import com.matheus.hackathonproject.domain.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class TransactionDtoAssembler {
    private ModelMapper modelMapper;

    public TransactionDtoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDto toDto(Transaction source) {
        return modelMapper.map(source, TransactionDto.class);
    }

    public List<TransactionDto> toDtoCollection(Collection<Transaction> source) {
        return source.stream()
                .map(transaction -> toDto(transaction))
                .toList();
    }
}
