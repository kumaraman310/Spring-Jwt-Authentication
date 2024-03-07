package com.jwt.authentication.web;

import com.jwt.authentication.dto.UserDto;
import com.jwt.authentication.dto.UserRequestDto;
import com.jwt.authentication.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-new-user")
    public UserDto registerNewUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.registerNewUser(userRequestDto);
    }

    @PostMapping("/assign-role")
    public UserDto assignRoleToUser(@RequestParam String userName, @RequestParam String roleName) {
        return userService.assignRoleToUser(userName, roleName);
    }

    @GetMapping("/for-admin")
    @PreAuthorize("hasRole('admin')")
    public String forAdmin(){
        return "This URL is only accessible to admin";
    }

    @GetMapping("/for-user")
    @PreAuthorize("hasRole('user')")
    public String forUser(){
        return "This URL is only accessible to user";
    }

}
