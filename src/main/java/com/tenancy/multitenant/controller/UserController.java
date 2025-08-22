package com.tenancy.multitenant.controller;

import com.tenancy.multitenant.dto.UserDTO;
import com.tenancy.multitenant.response.ApiResponse;
import com.tenancy.multitenant.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

}
