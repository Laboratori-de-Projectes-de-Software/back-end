package com.alia.back_end_service.domain.user.usecases;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.exceptions.InvalidPasswordException;
import com.alia.back_end_service.domain.user.exceptions.UserNotFoundException;
import com.alia.back_end_service.domain.user.ports.PasswordEncoderPort;
import com.alia.back_end_service.domain.user.ports.TokenProviderPort;
import com.alia.back_end_service.domain.user.ports.UserLoginPortAPI;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class LoginUserUseCase implements UserLoginPortAPI {
    private final UserPortDB userPortDB;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public User login(User userLogin) {
        User user = userPortDB.findByUsername(userLogin.getUsername())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoderPort.matches(userLogin.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        user.setToken(tokenProviderPort.generateToken(user));
        user.setExpiresIn(jwtExpiration);

        return user;
    }
}
