package com.alia.back_end_service.config;

import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import com.alia.back_end_service.domain.bot.usecases.GetAllBotUseCase;
import com.alia.back_end_service.domain.bot.usecases.GetBotUseCase;
import com.alia.back_end_service.domain.bot.usecases.RegisterBotUseCase;
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

    @Bean
    public RegisterBotUseCase registerBotUseCase(BotPortDB botPortDB) {
        return new RegisterBotUseCase(botPortDB);
    }

    @Bean
    public GetAllBotUseCase getAllBotUseCase(BotPortDB botPortDB) {return new GetAllBotUseCase(botPortDB);}

    @Bean
    public GetBotUseCase getBotUseCase(BotPortDB botPortDB) {return new GetBotUseCase(botPortDB);}
}
