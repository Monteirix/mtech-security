package com.mtech.security.service;

import com.mtech.security.entities.User;
import com.mtech.security.exception.EmailAlreadyExistsException;
import com.mtech.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user){
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if(userExist.isPresent()){
            throw new EmailAlreadyExistsException("Email already registered ");
        }

            user.setRole("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);



    }
}
