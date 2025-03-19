package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.application.services.AuthenticationService;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginRequest;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginResponse;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticateAndGenerateToken(
                loginRequest.getUsername() != null ? loginRequest.getUsername() : loginRequest.getEmail(),
                loginRequest.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest) {
        authenticationService.registerUser(registerRequest.getUsername(), registerRequest.getEmail(),
                registerRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}