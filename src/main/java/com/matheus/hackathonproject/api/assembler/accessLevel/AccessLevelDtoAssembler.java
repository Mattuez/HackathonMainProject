package com.matheus.hackathonproject.api.assembler.accessLevel;


import com.matheus.hackathonproject.api.model.accessLevel.AccessLevelDto;
import com.matheus.hackathonproject.domain.model.AccessLevel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AccessLevelDtoAssembler {

    private ModelMapper modelMapper;

    public AccessLevelDtoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccessLevelDto toDto(AccessLevel source) {
        return modelMapper.map(source, AccessLevelDto.class);
    }

    public List<AccessLevelDto> toDtoCollection(Collection<AccessLevel> source) {
        return source.stream()
                .map(accessLevel -> toDto(accessLevel))
                .toList();
    }
}
