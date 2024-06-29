package com.matheus.hackathonproject.api.controller;

import com.matheus.hackathonproject.api.assembler.accessLevel.AccessLevelDtoAssembler;
import com.matheus.hackathonproject.api.model.accessLevel.AccessLevelDto;
import com.matheus.hackathonproject.core.security.CheckSecurity;
import com.matheus.hackathonproject.domain.model.User;
import com.matheus.hackathonproject.domain.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/accessLevel")
public class UserAccessLevelController {

    private UserRegistrationService userRegistrationService;
    private AccessLevelDtoAssembler accessLevelDtoAssembler;

    public UserAccessLevelController(UserRegistrationService userRegistrationService,
                                     AccessLevelDtoAssembler accessLevelDtoAssembler) {
        this.userRegistrationService = userRegistrationService;
        this.accessLevelDtoAssembler = accessLevelDtoAssembler;
    }

    @CheckSecurity.UserGroupAccessLevel.canSearchSelf
    @GetMapping
    public List<AccessLevelDto> getAllByUserId(@PathVariable("userId") Long userId) {
        User user = userRegistrationService.search(userId);

        return accessLevelDtoAssembler.toDtoCollection(user.getAccessLevels());
    }

    @CheckSecurity.UserGroupAccessLevel.canAlter
    @PutMapping("/{accessLevelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable("userId") Long userId, @PathVariable("accessLevelId") Long accessLevelId) {
        userRegistrationService.associateAccessLevel(userId, accessLevelId);
    }

    @CheckSecurity.UserGroupAccessLevel.canAlter
    @DeleteMapping("/{accessLevelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable("userId") Long userId, @PathVariable("accessLevelId") Long accessLevelId) {
        userRegistrationService.disassociateAccessLevel(userId, accessLevelId);
    }
}
