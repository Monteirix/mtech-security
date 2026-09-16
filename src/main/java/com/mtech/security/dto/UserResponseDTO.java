package com.mtech.security.dto;

import com.mtech.security.entities.User;

public record UserResponseDTO(Long id, String name, String email, String role) {
   public UserResponseDTO(User user){

        this(user.getId(),
        user.getName(),
        user.getEmail(),
        user.getRole()
        );
    }

}
