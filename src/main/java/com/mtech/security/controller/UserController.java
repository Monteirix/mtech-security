package com.mtech.security.controller;

import com.mtech.security.dto.UserRegisterDTO;
import com.mtech.security.dto.UserResponseDTO;
import com.mtech.security.entities.User;
import com.mtech.security.mapper.UserMapper;
import com.mtech.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRegisterDTO dto) {
        User user = userMapper.toEntity(dto);
        User userSaved= userService.save(user);

        UserResponseDTO response = userMapper.toResponseDTO(userSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/protected")
    public UserResponseDTO protectedRoute() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

            return new UserResponseDTO(user);
    }
}
