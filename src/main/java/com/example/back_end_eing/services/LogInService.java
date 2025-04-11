package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.LogInUserDto;
import com.example.back_end_eing.dto.RegisterUserDto;
import com.example.back_end_eing.dto.UserResponseDTO;

public interface LogInService {
    public UserResponseDTO signUp(RegisterUserDto registerUserDto);
    public UserResponseDTO logIn(LogInUserDto logInUserDto);


}
