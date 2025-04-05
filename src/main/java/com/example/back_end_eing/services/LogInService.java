package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.UserDTOLogin;
import com.example.back_end_eing.dto.UserDTORegister;
import com.example.back_end_eing.dto.UserResponseDTO;

public interface LogInService {
    public void signUp(UserDTORegister userDTORegister);
    public UserResponseDTO logIn(UserDTOLogin userDTOLogin);


}
