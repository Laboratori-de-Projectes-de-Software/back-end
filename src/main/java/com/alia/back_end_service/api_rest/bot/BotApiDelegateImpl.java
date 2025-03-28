package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api.BotsApiDelegate;
import com.alia.back_end_service.api_model.BotRegister;
import com.alia.back_end_service.api_model.BotReturn;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetAllPortAPI;
import com.alia.back_end_service.domain.bot.port.BotGetPortApi;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class BotApiDelegateImpl implements BotsApiDelegate {
    private final BotRegistrationPortAPI botRegistrationPortAPI;
    private final BotGetAllPortAPI botGetAllPortAPI;
    private final BotGetPortApi botGetPortApi;

    @Override
    public ResponseEntity<BotReturn> botsRegisterPost(BotRegister botRegister) {
        // Mapeo al objeto de dominio
        Bot bot = new Bot(
                botRegister.getName(),
                botRegister.getDescription(),
                botRegister.getEndpoint(),
                UUID.randomUUID().toString(),    // Se genera un token (aquí podrías invocar un servicio especializado)
                botRegister.getUserId(),
                Collections.emptyList()          // Inicialmente, sin ligas asociadas
        );
        botRegistrationPortAPI.registerBot(bot);
        BotReturn response = new BotReturn();
        response.setName(bot.getName());
        response.setDescription(bot.getDescription());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<List<BotReturn>> botsAllGet() {
        List<Bot> bots = botGetAllPortAPI.getAllBots();
        List<BotReturn> botReturns = new ArrayList<>();
        BotReturn returnBot;
        for (Bot bot : bots) {
            returnBot = new BotReturn();
            returnBot.setName(bot.getName());
            returnBot.setDescription(bot.getDescription());
            botReturns.add(returnBot);
        }
        return ResponseEntity.status(HttpStatus.OK).body(botReturns);
    }

    @Override
    public ResponseEntity<BotReturn> botsIdGet(String id) {
        Bot bot = botGetPortApi.findByName(id);
        BotReturn returnBot = new BotReturn();
        returnBot.setName(bot.getName());
        returnBot.setDescription(bot.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(returnBot);
    }
}
