package com.matheus.hackathonproject.domain.exceptions;

public class EntityBeingUsedException extends BusinessException {

    public EntityBeingUsedException(String message) {
        super(message);
    }

    public EntityBeingUsedException(String entity, Long id) {
        this("%s with code %d is being used and can't be removed".formatted(entity, id));
    }
}
