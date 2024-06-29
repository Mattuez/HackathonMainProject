package com.matheus.hackathonproject.domain.exceptions;

public class UserNotFoundException extends EntityNotFoundException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        this("User with id %d doesn't exist".formatted(userId));
    }
}
