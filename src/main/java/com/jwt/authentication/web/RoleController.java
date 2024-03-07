package com.jwt.authentication.web;

import com.jwt.authentication.dto.RoleDto;
import com.jwt.authentication.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create-new-role")
    public RoleDto createNewRole(@RequestBody RoleDto roleDto) {
        return roleService.createNewRole(roleDto);
    }

}
