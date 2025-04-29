package com.alia.back_end_service.domain.user.usecases;

import com.alia.back_end_service.domain.user.ports.PasswordEncoderPort;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import com.alia.back_end_service.domain.user.exceptions.EmailAlreadyExistsException;
import com.alia.back_end_service.domain.user.exceptions.UsernameAlreadyExistsException;
import com.alia.back_end_service.domain.user.ports.UserRegistrationPortAPI;
import lombok.RequiredArgsConstructor;

import java.util.Collections;


@RequiredArgsConstructor
public class RegisterUserUseCase implements UserRegistrationPortAPI {
    private final UserPortDB userPortDB;
    private final PasswordEncoderPort passwordEncoderPort;

    public User registerUser(User user) {
        if (userPortDB.findByEmail(user.getMail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        if (userPortDB.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        // Encriptar contraseña antes de guardarla
        String encryptedPassword = passwordEncoderPort.encode(user.getPassword());
        user = new User(user.getUsername(), user.getMail(), encryptedPassword, user.getRole(), Collections.emptyList());

        return userPortDB.save(user);
    }
}
