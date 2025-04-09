package com.alia.back_end_service.config;

import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.bot.usecases.*;
import com.alia.back_end_service.domain.game.ports.GamePortDB;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.usecases.*;
import com.alia.back_end_service.domain.message.port.MessagePortDB;
import com.alia.back_end_service.domain.message.usecases.GetAllMessagesFromMatch;
import com.alia.back_end_service.domain.round.ports.RoundPortDB;
import com.alia.back_end_service.domain.user.ports.PasswordEncoderPort;
import com.alia.back_end_service.domain.user.ports.TokenProviderPort;
import com.alia.back_end_service.domain.user.usecases.*;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
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
    public BotGetAllByUserUseCase getAllUserBotsUseCase(BotPortDB botPortDB) {
        return new BotGetAllByUserUseCase(botPortDB);
    }

    @Bean
    GetAllByUserUseCase getAllUserLeaguesUseCase(LeaguePortDB leaguePortDB) {
        return new GetAllByUserUseCase(leaguePortDB);
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
    @Bean
    UpdateBotUseCase updateBotUseCase(BotPortDB botPortDB) {return new UpdateBotUseCase(botPortDB);}

    //LEAGUE
    @Bean
    public CreateLeagueUseCase createLeagueUseCase(LeaguePortDB leaguePortDB) {return new CreateLeagueUseCase(leaguePortDB);}
    @Bean
    public GetAllLeagueUseCase getAllLeagueUseCase(LeaguePortDB leaguePortDB) {return new GetAllLeagueUseCase(leaguePortDB);}
    @Bean
    public GetLeagueUseCase getLeagueUseCase(LeaguePortDB leaguePortDB) {return new GetLeagueUseCase(leaguePortDB);}
    @Bean
    public InscribeBotUseCase inscribeBotUseCase(LeaguePortDB leaguePortDB) {return new InscribeBotUseCase(leaguePortDB);}
    @Bean
    public UpdateLeagueUseCase updateLeagueUseCase(LeaguePortDB leaguePortDB) {return new UpdateLeagueUseCase(leaguePortDB);}
    @Bean
    public GetAllLeagueBotsUseCase getAllLeagueBotsUseCase(LeaguePortDB leaguePortDB) {return new GetAllLeagueBotsUseCase(leaguePortDB);}
    @Bean
    public DeleteLeagueUseCase deleteLeagueUseCase(LeaguePortDB leaguePortDB) {return new DeleteLeagueUseCase(leaguePortDB);}
    @Bean
    public StartLeagueUseCase startLeagueUseCase(LeaguePortDB leaguePortDB, RoundPortDB roundPortDB, GamePortDB gamePortDB) { return new StartLeagueUseCase(leaguePortDB, roundPortDB, gamePortDB); }
    @Bean
    public GetAllLeagueGamesUseCase getAllLeagueGamesUseCase(LeaguePortDB leaguePortDB){return new GetAllLeagueGamesUseCase(leaguePortDB);}

    //Message
    @Bean
    public GetAllMessagesFromMatch getAllMessagesFromMatch(MessagePortDB messagePortDB) {return new GetAllMessagesFromMatch(messagePortDB);}
}
