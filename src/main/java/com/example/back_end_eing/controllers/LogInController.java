package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.LogInUserDto;
import com.example.back_end_eing.dto.RegisterUserDto;
import com.example.back_end_eing.dto.UserResponseDTO;
import com.example.back_end_eing.services.LogInService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v0/auth")
public class LogInController {

    private LogInService logInService;

    @PostMapping("/signup")
    public UserResponseDTO registerUser(@RequestBody RegisterUserDto registerUser){
        return logInService.signUp(registerUser);
    }


    @PostMapping("/login")
    public UserResponseDTO loginUser(@RequestBody LogInUserDto logInUserDto) {
        System.out.println("Hola");
        return logInService.logIn(logInUserDto);
    }
}
