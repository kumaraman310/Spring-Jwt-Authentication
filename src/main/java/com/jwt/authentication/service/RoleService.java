package com.jwt.authentication.service;

import com.jwt.authentication.adapter.RoleAdapter;
import com.jwt.authentication.dto.RoleDto;
import com.jwt.authentication.repository.RoleDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleDetailRepository roleDetailRepository;
    private final RoleAdapter roleAdapter;

    public RoleService(RoleDetailRepository roleDetailRepository, RoleAdapter roleAdapter) {
        this.roleDetailRepository = roleDetailRepository;
        this.roleAdapter = roleAdapter;
    }

    public RoleDto createNewRole(RoleDto roleDto) {
        return roleAdapter.getRoleDto(roleDetailRepository.save(roleAdapter.getRoleEntity(roleDto)));
    }
}
