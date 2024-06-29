package com.matheus.hackathonproject.api.controller;

import com.matheus.hackathonproject.api.assembler.user.UserDtoAssembler;
import com.matheus.hackathonproject.api.assembler.user.UserDtoDisassembler;
import com.matheus.hackathonproject.api.model.user.UserAddInputDto;
import com.matheus.hackathonproject.api.model.user.UserChangePasswordDto;
import com.matheus.hackathonproject.api.model.user.UserDto;
import com.matheus.hackathonproject.api.model.user.UserUpdateInputDto;
import com.matheus.hackathonproject.domain.exceptions.AccessLevelNotFoundException;
import com.matheus.hackathonproject.domain.exceptions.BusinessException;
import com.matheus.hackathonproject.domain.model.User;
import com.matheus.hackathonproject.domain.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController{

    private UserRegistrationService userService;
    private UserDtoAssembler userDtoAssembler;
    private UserDtoDisassembler userDtoDisassembler;

    public UserController(UserRegistrationService userService, UserDtoAssembler userDtoAssembler, UserDtoDisassembler userDtoDisassembler) {
        this.userService = userService;
        this.userDtoAssembler = userDtoAssembler;
        this.userDtoDisassembler = userDtoDisassembler;
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userDtoAssembler.toDtoCollection(userService.searchAll());
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable("userId") Long userId) {
        return userDtoAssembler.toDto(userService.search(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@RequestBody @Valid UserAddInputDto userInputDto) {
        try {
            User user = userDtoDisassembler.toEntityObject(userInputDto);

            return userDtoAssembler.toDto(userService.insert(user));
        } catch (AccessLevelNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public UserDto update(@PathVariable("userId") Long userId,
                          @RequestBody @Valid UserUpdateInputDto source) {
        try {
            User existingUser = userService.search(userId);

            userDtoDisassembler.copyToEntityObject(source, existingUser);

            return userDtoAssembler.toDto(userService.insert(existingUser));
        } catch (AccessLevelNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("userId") Long userId) {
        userService.exclude(userId);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable("userId") Long userId,
                               @RequestBody @Valid UserChangePasswordDto userChangePasswordDto) {
        userService.alterPassword(userId, userChangePasswordDto);
    }
}
