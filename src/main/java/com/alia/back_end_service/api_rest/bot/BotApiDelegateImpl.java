package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api.BotsApiDelegate;
import com.alia.back_end_service.api_model.BotRegister;
import com.alia.back_end_service.api_model.BotReturn;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetAllPortAPI;
import com.alia.back_end_service.domain.bot.port.BotGetPortApi;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeagueGetPortAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BotApiDelegateImpl implements BotsApiDelegate {
    private final BotRegistrationPortAPI botRegistrationPortAPI;
    private final BotGetAllPortAPI botGetAllPortAPI;
    private final BotGetPortApi botGetPortApi;
    private final BotMapperAPI botMapperPortAPI;

    private final LeagueGetPortAPI leagueGetPortAPI;

    @Override
    public ResponseEntity<BotReturn> botsRegisterPost(BotRegister botRegister) {
        Bot bot = botRegistrationPortAPI.registerBot(botMapperPortAPI.toDomainRegister(botRegister));
        return ResponseEntity.status(HttpStatus.CREATED).body(botMapperPortAPI.toApiResponse(bot));
    }

    @Override
    public ResponseEntity<List<BotReturn>> botsAllGet() {
        return ResponseEntity.status(HttpStatus.OK).body(botGetAllPortAPI.getAllBots()
                .stream()
                .map(botMapperPortAPI::toApiResponse)
                .toList());
    }

    @Override
    public ResponseEntity<BotReturn> botsIdGet(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(botMapperPortAPI.toApiResponse(botGetPortApi.findByName(id)));
    }

}
