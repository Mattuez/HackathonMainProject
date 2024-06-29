package com.matheus.hackathonproject.api.model.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionInputDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}