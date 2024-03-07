package com.jwt.authentication.service;

import com.jwt.authentication.adapter.RoleAdapter;
import com.jwt.authentication.adapter.UserAdapter;
import com.jwt.authentication.dto.RoleDto;
import com.jwt.authentication.dto.UserDto;
import com.jwt.authentication.dto.UserRequestDto;
import com.jwt.authentication.entity.RoleEntity;
import com.jwt.authentication.entity.UserEntity;
import com.jwt.authentication.repository.RoleDetailRepository;
import com.jwt.authentication.repository.UserDetailRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserDetailRepository userDetailRepository;

    private final RoleDetailRepository roleDetailRepository;
    private final UserAdapter userAdapter;

    private final RoleAdapter roleAdapter;

    public UserService(UserDetailRepository userDetailRepository, RoleDetailRepository roleDetailRepository, UserAdapter userAdapter, RoleAdapter roleAdapter) {
        this.userDetailRepository = userDetailRepository;
        this.roleDetailRepository = roleDetailRepository;
        this.userAdapter = userAdapter;
        this.roleAdapter = roleAdapter;
    }

    public UserDto registerNewUser(UserRequestDto userRequestDto) {

        UserDto userDto = userAdapter.getUserDtoFromRequest(userRequestDto);

        RoleDto roleDto = roleAdapter.getRoleDto(roleDetailRepository.findById("user").get());
        Set<RoleDto> role = new HashSet<>();
        role.add(roleDto);
        userDto.setRole(role);

        return userAdapter.getUserDto(userDetailRepository.save(userAdapter.getUserEntity(userDto)));
    }

    public UserDto assignRoleToUser(String userName, String roleName) {
        Optional<UserEntity> optionalUser = userDetailRepository.findById(userName);
        Optional<RoleEntity> optionalRole = roleDetailRepository.findById(roleName);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            RoleEntity roleEntity = optionalRole.get();

            userEntity.getRole().add(roleEntity);
            userDetailRepository.save(userEntity);

            return userAdapter.getUserDto(userEntity);
        } else {
            throw new IllegalArgumentException("User or Role not found");
        }
    }
}
