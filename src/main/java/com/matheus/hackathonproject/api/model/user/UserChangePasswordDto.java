package com.matheus.hackathonproject.api.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordDto {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
