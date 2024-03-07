package com.jwt.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    private String roleName;

    private String roleDescription;
}
