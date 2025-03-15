package com.alia.back_end_service.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserPortDB userPortDB;

    public User execute(User user) {
        if (userPortDB.findByEmail(user.getMail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (userPortDB.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El username ya está registrado");
        }

        return userPortDB.saveUser(user);
    }
}
