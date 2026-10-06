package com.mtech.security.controller;

import com.mtech.security.dto.LoginRequestDTO;
import com.mtech.security.entities.User;
import com.mtech.security.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        }

    @Operation(
            summary = "Authenticate user",
            description = "Authenticates a user and returns a JWT token."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password")
    })

    @PostMapping
    public String login(@RequestBody LoginRequestDTO request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                );

        var authentication =
                authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generetadeToken((User) authentication.getPrincipal());

        return token;
    }

}



