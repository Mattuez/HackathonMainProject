package com.matheus.hackathonproject.api.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserUpdateInputDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
