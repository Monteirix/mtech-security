package com.mtech.security.controller;

import com.mtech.security.dto.UserRegisterDTO;
import com.mtech.security.dto.UserResponseDTO;
import com.mtech.security.entities.User;
import com.mtech.security.mapper.UserMapper;
import com.mtech.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully registered"),
            @ApiResponse(responseCode = "409", description = "Email already registered")
    })


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRegisterDTO dto) {
        User user = userMapper.toEntity(dto);
        User userSaved= userService.save(user);

        UserResponseDTO response = userMapper.toResponseDTO(userSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get authenticated user",
            description = "Returns the data of the currently authenticated user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated user returned"),
            @ApiResponse(responseCode = "401", description = "Authentication required")
    })
    @SecurityRequirement(name = "bearer-key")

    @GetMapping("/protected")
    public UserResponseDTO protectedRoute() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

            return new UserResponseDTO(user);
    }
}
