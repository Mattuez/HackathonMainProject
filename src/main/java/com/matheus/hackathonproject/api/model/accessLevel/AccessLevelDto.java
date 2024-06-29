package com.matheus.hackathonproject.api.model.accessLevel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessLevelDto {
    @NotBlank
    private String name;
}
