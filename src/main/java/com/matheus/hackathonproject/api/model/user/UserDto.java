package com.matheus.hackathonproject.api.model.user;

import com.matheus.hackathonproject.api.model.accessLevel.AccessLevelDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private List<AccessLevelDto> accessLevels;
}
