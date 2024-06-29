package com.matheus.hackathonproject.api.assembler.permission;

import com.matheus.hackathonproject.api.model.permission.PermissionInputDto;
import com.matheus.hackathonproject.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermissionDtoDisassembler {

    private ModelMapper modelMapper;

    public PermissionDtoDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Permission toEntityObject(PermissionInputDto source) {
        return modelMapper.map(source, Permission.class);
    }

    public void copyToEntityObject(PermissionInputDto source, Permission destination) {
        modelMapper.map(source, destination);
    }
}
