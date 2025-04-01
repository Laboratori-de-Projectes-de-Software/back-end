package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.LogInUserDto;
import com.example.back_end_eing.dto.RegisterUserDto;
import com.example.back_end_eing.services.LogInService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class LogInController {

    private LogInService logInService;


    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto registerUser
                                               ) {
        try {
            logInService.signUp(registerUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LogInUserDto logInUserDto
                                            ) {
        try {
            // Realizar el inicio de sesión
            String jwtToken = logInService.logIn(logInUserDto);
            return ResponseEntity.ok("Inicio de sesión exitoso. Token: " + jwtToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error al iniciar sesión: " + e.getMessage());
        }
    }
}
