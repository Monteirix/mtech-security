package com.mtech.security.controller;

import com.mtech.security.entities.User;
import com.mtech.security.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping

    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/protected")
    public String protectedRoute() {
            return"voce esta autenticado";
}
}
