package com.jwt.authentication.service;

import com.jwt.authentication.dto.JwtRequestDto;
import com.jwt.authentication.dto.JwtResponseDto;
import com.jwt.authentication.entity.UserEntity;
import com.jwt.authentication.repository.UserDetailRepository;
import com.jwt.authentication.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class JwtService {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<JwtResponseDto> generateToken(JwtRequestDto jwtRequest) throws Exception {

        log.info("Inside Controller");
        log.info("Jwt Request Params" + jwtRequest);
        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        // Get UserDetail to generate token
        final UserDetails userDetails = customUserDetailService
                .loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT " + token);

        //{"token":"value"}
        return new ResponseEntity<>(new JwtResponseDto(token), HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
