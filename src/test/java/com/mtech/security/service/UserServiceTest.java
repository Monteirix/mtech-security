package com.mtech.security.service;

import com.mtech.security.entities.User;
import com.mtech.security.exception.EmailAlreadyExistsException;
import com.mtech.security.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        User user =  new User();
        user.setEmail("teste@gmail.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyExistsException.class,()-> userService.save(user));

    }
    @Test
    void shouldSaveUserWhenEmailDoesNotExist() {

        // Arrange
        User user = new User();
        user.setEmail("novo@email.com");
        user.setPassword("123456");

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(user.getPassword()))
                .thenReturn("encodedPassword");

        when(userRepository.save(user))
                .thenReturn(user);

        // Act
        User savedUser = userService.save(user);

        // Assert
        assertEquals(user, savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());

        verify(userRepository).findByEmail(user.getEmail());
        verify(passwordEncoder).encode("123456");
        verify(userRepository).save(user);
    }

    @Test
    void shouldNotSaveUserWhenEmailAlreadyExists() {

        // Arrange
        User user = new User();
        user.setEmail("teste@email.com");

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        // Act + Assert
        assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.save(user)
        );

        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

}