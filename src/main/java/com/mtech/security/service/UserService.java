package com.mtech.security.service;

import com.mtech.security.entities.User;
import com.mtech.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
