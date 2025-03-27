package com.alia.back_end_service.config;

import com.alia.back_end_service.domain.user.ports.PasswordEncoderPort;
import com.alia.back_end_service.domain.user.ports.TokenProviderPort;
import com.alia.back_end_service.domain.user.usecases.LoginUserUseCase;
import com.alia.back_end_service.domain.user.usecases.RegisterUserUseCase;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import com.alia.back_end_service.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserPortDB userPortDB, PasswordEncoderPort passwordEncoderPort) {
        return new RegisterUserUseCase(userPortDB, passwordEncoderPort);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(UserPortDB userPortDB, PasswordEncoderPort passwordEncoderPort, TokenProviderPort tokenProviderPort) {
        return new LoginUserUseCase(userPortDB, passwordEncoderPort, tokenProviderPort);
    }
}
