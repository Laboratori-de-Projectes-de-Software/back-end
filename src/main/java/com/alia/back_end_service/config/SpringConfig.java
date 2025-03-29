package com.alia.back_end_service.config;

import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import com.alia.back_end_service.domain.bot.usecases.GetAllBotUseCase;
import com.alia.back_end_service.domain.bot.usecases.GetBotUseCase;
import com.alia.back_end_service.domain.bot.usecases.RegisterBotUseCase;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.usecases.CreateLeagueUseCase;
import com.alia.back_end_service.domain.league.usecases.GetAllLeagueUseCase;
import com.alia.back_end_service.domain.league.usecases.GetLeagueUseCase;
import com.alia.back_end_service.domain.user.ports.PasswordEncoderPort;
import com.alia.back_end_service.domain.user.ports.TokenProviderPort;
import com.alia.back_end_service.domain.user.usecases.GetAllUserBotsUseCase;
import com.alia.back_end_service.domain.user.usecases.GetUserUseCase;
import com.alia.back_end_service.domain.user.usecases.LoginUserUseCase;
import com.alia.back_end_service.domain.user.usecases.RegisterUserUseCase;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import com.alia.back_end_service.jwt.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // USER
    @Bean
    public RegisterUserUseCase registerUserUseCase(UserPortDB userPortDB, PasswordEncoderPort passwordEncoderPort) {
        return new RegisterUserUseCase(userPortDB, passwordEncoderPort);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(UserPortDB userPortDB, PasswordEncoderPort passwordEncoderPort, TokenProviderPort tokenProviderPort) {
        return new LoginUserUseCase(userPortDB, passwordEncoderPort, tokenProviderPort);
    }

    @Bean
    public GetUserUseCase getUserUseCase(UserPortDB userPortDB) {
        return new GetUserUseCase(userPortDB);
    }

    @Bean
    public GetAllUserBotsUseCase getAllUserBotsUseCase(BotPortDB botPortDB) {
        return new GetAllUserBotsUseCase(botPortDB);
    }


    // BOT

    @Bean
    public RegisterBotUseCase registerBotUseCase(BotPortDB botPortDB) {
        return new RegisterBotUseCase(botPortDB);
    }

    @Bean
    public GetAllBotUseCase getAllBotUseCase(BotPortDB botPortDB) {return new GetAllBotUseCase(botPortDB);}

    @Bean
    public GetBotUseCase getBotUseCase(BotPortDB botPortDB) {return new GetBotUseCase(botPortDB);}

    //LEAGUE
    @Bean
    public CreateLeagueUseCase createLeagueUseCase(LeaguePortDB leaguePortDB) {return new CreateLeagueUseCase(leaguePortDB);}
    @Bean
    public GetAllLeagueUseCase getAllLeagueUseCase(LeaguePortDB leaguePortDB) {return new GetAllLeagueUseCase(leaguePortDB);}
    @Bean
    public GetLeagueUseCase getLeagueUseCase(LeaguePortDB leaguePortDB) {return new GetLeagueUseCase(leaguePortDB);}
}
