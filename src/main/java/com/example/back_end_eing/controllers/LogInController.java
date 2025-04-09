package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.UserDTOLogin;
import com.example.back_end_eing.dto.UserDTORegister;
import com.example.back_end_eing.dto.UserResponseDTO;
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


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTORegister registerUser
                                               ) {
        try {
            logInService.signUp(registerUser);
            return ResponseEntity.status(HttpStatus.OK).body("User created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al registrar el usuario: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserDTOLogin userDTOLogin
                                            ) {
        try {
            // Realizar el inicio de sesión
            return ResponseEntity.ok(logInService.logIn(userDTOLogin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
