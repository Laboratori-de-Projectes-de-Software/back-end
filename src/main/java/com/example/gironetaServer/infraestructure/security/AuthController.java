package com.example.gironetaServer.infraestructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gironetaServer.application.services.AuthenticationService;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.JwtResponseDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginRequestDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.RegisterRequestDto;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        String token = authenticationService.authenticateAndGenerateToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequest) {
        // Registrar el nuevo usuario
        authenticationService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword());

        // Autenticar al usuario y generar token JWT inmediatamente
        String token = authenticationService.authenticateAndGenerateToken(
                registerRequest.getUsername(),
                registerRequest.getPassword());

        // Devolver el token para inicio de sesión automático
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}