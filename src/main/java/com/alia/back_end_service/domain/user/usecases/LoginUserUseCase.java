package com.alia.back_end_service.domain.user.usecases;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.exceptions.InvalidPasswordException;
import com.alia.back_end_service.domain.user.exceptions.UserNotFoundException;
import com.alia.back_end_service.domain.user.ports.PasswordEncoderPort;
import com.alia.back_end_service.domain.user.ports.TokenProviderPort;
import com.alia.back_end_service.domain.user.ports.UserLoginPortAPI;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUserUseCase implements UserLoginPortAPI {
    private final UserPortDB userPortDB;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    public String login(String username, String rawPassword) {
        User user = userPortDB.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoderPort.matches(rawPassword, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return tokenProviderPort.generateToken(user);
    }
}
