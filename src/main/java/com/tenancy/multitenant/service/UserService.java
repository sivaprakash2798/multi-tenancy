package com.tenancy.multitenant.service;

import com.tenancy.multitenant.dto.UserDTO;
import com.tenancy.multitenant.exception.CustomException;
import com.tenancy.multitenant.model.User;
import com.tenancy.multitenant.repository.UserRepository;
import com.tenancy.multitenant.response.ApiResponse;
import com.tenancy.multitenant.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<ApiResponse> createUser(UserDTO userDTO) {
        User user = new User();
        if(userRepository.existsByUserName(userDTO.getUserName())){
            throw new CustomException("Username already exists");
        }
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setTenantId(userDTO.getTenantId());
        user.setRoleName(userDTO.getRoleName());
        user.setIsActive(true);

        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return CommonUtil.getOkResponse("User created", "");
    }


}
