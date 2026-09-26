package com.mtech.security.service;

import com.mtech.security.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    private TokenService tokenService;
    private String secret;

    @BeforeEach
    void setUp(){
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret","01234567890123456789012345678901");


    }

    @Test
    void generetadeToken() {
        User user = new User();
        user.setEmail("teste@email.com");

        String generetadeToken = tokenService.generetadeToken(user);

        assertNotNull(generetadeToken);
        assertFalse(generetadeToken.isEmpty());
    }


    @Test
    void getSubject(){
        User user = new User();
        user.setEmail("teste@email.com");

        String generetadeToken = tokenService.generetadeToken(user);
        String subject = tokenService.getSubject(generetadeToken);


        assertEquals("teste@email.com", subject);
    }
}