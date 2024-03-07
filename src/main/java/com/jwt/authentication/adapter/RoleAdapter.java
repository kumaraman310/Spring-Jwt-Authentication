package com.jwt.authentication.adapter;

import com.jwt.authentication.dto.RoleDto;
import com.jwt.authentication.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class RoleAdapter {

    private final ModelMapper modelMapper;

    public RoleAdapter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleEntity getRoleEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, RoleEntity.class);
    }

    public RoleDto getRoleDto(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleDto.class);
    }
}
