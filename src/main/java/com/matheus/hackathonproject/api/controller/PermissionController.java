package com.matheus.hackathonproject.api.controller;

import com.matheus.hackathonproject.api.assembler.permission.PermissionDtoAssembler;
import com.matheus.hackathonproject.api.assembler.permission.PermissionDtoDisassembler;
import com.matheus.hackathonproject.api.model.permission.PermissionDto;
import com.matheus.hackathonproject.core.security.CheckSecurity;
import com.matheus.hackathonproject.domain.service.PermissionRegistrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private PermissionRegistrationService permissionRegistrationService;
    private PermissionDtoAssembler permissionDtoAssembler;
    private PermissionDtoDisassembler permissionDtoDisassembler;

    public PermissionController(PermissionRegistrationService permissionRegistrationService,
                                PermissionDtoAssembler permissionDtoAssembler,
                                PermissionDtoDisassembler permissionDtoDisassembler) {
        this.permissionRegistrationService = permissionRegistrationService;
        this.permissionDtoAssembler = permissionDtoAssembler;
        this.permissionDtoDisassembler = permissionDtoDisassembler;
    }

    @CheckSecurity.UserGroupAccessLevel.canSearch
    @GetMapping
    public List<PermissionDto> getAll() {
        return permissionDtoAssembler.toDtoCollection(permissionRegistrationService.searchAll());
    }

    @CheckSecurity.UserGroupAccessLevel.canSearch
    @GetMapping("/{permissionId}")
    public PermissionDto getById(@PathVariable("permissionId") Long permissionId) {
        return permissionDtoAssembler.toDto(permissionRegistrationService.search(permissionId));
    }
}
