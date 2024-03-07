package com.jwt.authentication.service;

import com.jwt.authentication.dto.JwtRequestDto;
import com.jwt.authentication.dto.JwtResponseDto;
import com.jwt.authentication.entity.UserEntity;
import com.jwt.authentication.repository.UserDetailRepository;
import com.jwt.authentication.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = this.userDetailRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getUserPassword(),
                    getAuthorities(user));
        }
    }

    public Set getAuthorities(UserEntity userEntity) {
        Set authorities = new HashSet();

        userEntity.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
}
