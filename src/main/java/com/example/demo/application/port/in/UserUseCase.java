package com.example.demo.application.port.in;

import com.example.demo.dtos.UserDTOLogin;
import com.example.demo.dtos.UserDTORegister;
import com.example.demo.dtos.UserResponseDTO;

import java.util.List;

/**
 * Define los casos de uso para la gestión de usuarios.
 */
public interface UserUseCase {
    UserDTORegister register(UserDTORegister userDTORegister);
    UserDTOLogin login(UserDTOLogin userDTOLogin);
}
