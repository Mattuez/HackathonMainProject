package com.matheus.hackathonproject.domain.exceptions;

public class PermissionNotFoundException extends EntityNotFoundException{
    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("Permission with id %d doesn't exist", permissionId));
    }
}
