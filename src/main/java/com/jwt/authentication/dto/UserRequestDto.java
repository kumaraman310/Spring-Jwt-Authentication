package com.jwt.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwt.authentication.entity.RoleEntity;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {

    private String userName;

    private String userFirstName;

    private String userLastName;

    private String userPassword;
}
