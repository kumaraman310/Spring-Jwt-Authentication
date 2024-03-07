package com.jwt.authentication.adapter;

import com.jwt.authentication.dto.RoleDto;
import com.jwt.authentication.dto.UserDto;
import com.jwt.authentication.dto.UserRequestDto;
import com.jwt.authentication.entity.RoleEntity;
import com.jwt.authentication.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserAdapter(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getUserEntity(UserDto userDto) {

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setUserPassword(getEncodepassword(userDto.getUserPassword()));
        return userEntity;
    }

    public UserDto getUserDto(UserEntity userEntity) {

        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserDto getUserDtoFromRequest(UserRequestDto userRequestDto) {

        return modelMapper.map(userRequestDto, UserDto.class);
    }

    private String getEncodepassword(String password) {
        return passwordEncoder.encode(password);
    }

}