package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.UserDTOLogin;
import com.example.back_end_eing.dto.UserDTORegister;
import com.example.back_end_eing.dto.UserResponseDTO;
import com.example.back_end_eing.services.LogInService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v0/auth")
public class LogInController {

    private LogInService logInService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(@RequestBody UserDTORegister registerUser){
        return logInService.signUp(registerUser);
    }


    @PostMapping("/login")
    public UserResponseDTO loginUser(@RequestBody UserDTOLogin userDTOLogin) {
        return logInService.logIn(userDTOLogin);
    }
}
