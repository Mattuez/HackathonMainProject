package com.matheus.hackathonproject.domain.exceptions;

public class AccessLevelNotFoundException extends EntityNotFoundException{
    public AccessLevelNotFoundException(String message) {
        super(message);
    }

    public AccessLevelNotFoundException(Long accessLevelId) {
        this("Access level with id %d doesn't exist".formatted(accessLevelId));
    }
}
