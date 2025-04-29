package com.developers.iasuperleague.application.port.in;

import com.developers.iasuperleague.dtos.UserDTOLogin;
import com.developers.iasuperleague.dtos.UserDTORegister;

/**
 * Define los casos de uso para la gestión de usuarios.
 */
public interface UserUseCase {
    UserDTORegister register(UserDTORegister userDTORegister);
    UserDTOLogin login(UserDTOLogin userDTOLogin);
}
