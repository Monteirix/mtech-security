package com.mtech.security.mapper;

import com.mtech.security.dto.UserRegisterDTO;
import com.mtech.security.dto.UserResponseDTO;
import com.mtech.security.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRegisterDTO dto) {
        User user = new User();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return user;
    }
    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
