package com.jwt.authentication.web;

import com.jwt.authentication.dto.JwtRequestDto;
import com.jwt.authentication.dto.JwtResponseDto;
import com.jwt.authentication.service.JwtService;
import com.jwt.authentication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {


    @Autowired
    private JwtService jwtService;

    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> generateToken(@RequestBody JwtRequestDto jwtRequest) throws Exception {

        return jwtService.generateToken(jwtRequest);

    }
}
