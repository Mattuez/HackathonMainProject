package com.matheus.hackathonproject.api.assembler.permission;

import com.matheus.hackathonproject.api.model.permission.PermissionDto;
import com.matheus.hackathonproject.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PermissionDtoAssembler {

    private ModelMapper modelMapper;

    public PermissionDtoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PermissionDto toDto(Permission source) {
        return modelMapper.map(source, PermissionDto.class);
    }

    public List<PermissionDto> toDtoCollection(Collection<Permission> source) {
        return source.stream()
                .map(permission -> toDto(permission))
                .toList();
    }
}
