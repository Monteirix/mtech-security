package com.mtech.security.controller;

import com.mtech.security.dto.UserRegisterDTO;
import com.mtech.security.dto.UserResponseDTO;
import com.mtech.security.entities.User;
import com.mtech.security.mapper.UserMapper;
import com.mtech.security.repositories.UserRepository;
import com.mtech.security.service.TokenService;
import com.mtech.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void shouldRegisterUser() throws Exception {

        UserRegisterDTO dto =
                new UserRegisterDTO("Matheus", "teste@email.com", "123456");

        User user = new User();
        user.setEmail("teste@email.com");

        UserResponseDTO response = new UserResponseDTO(user);

        when(userMapper.toEntity(any(UserRegisterDTO.class)))
                .thenReturn(user);

        when(userService.save(user))
                .thenReturn(user);

        when(userMapper.toResponseDTO(user))
                .thenReturn(response);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
                MockMvcResultMatchers.status().isCreated();

    }
}