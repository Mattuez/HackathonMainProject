package com.matheus.hackathonproject.api.assembler.accessLevel;

import com.matheus.hackathonproject.api.model.accessLevel.AccessLevelInputDto;
import com.matheus.hackathonproject.domain.model.AccessLevel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccessLevelDtoDisassembler {

    private ModelMapper modelMapper;

    public AccessLevelDtoDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccessLevel toEntityObject(AccessLevelInputDto source) {
        return modelMapper.map(source, AccessLevel.class);
    }

    public void copyToEntityObject(AccessLevelInputDto source, AccessLevel destination) {
        modelMapper.map(source, destination);
    }
}
