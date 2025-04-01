package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.LogInUserDto;
import com.example.back_end_eing.dto.RegisterUserDto;

public interface LogInService {
    public void signUp(RegisterUserDto registerUserDto);
    public String logIn(LogInUserDto logInUserDto);


}
