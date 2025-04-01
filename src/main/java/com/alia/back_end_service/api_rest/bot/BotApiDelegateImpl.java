package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api.BotApiDelegate;
import com.alia.back_end_service.api_model.BotDTO;
import com.alia.back_end_service.api_model.BotSummaryDTO;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetAllPortAPI;
import com.alia.back_end_service.domain.bot.port.BotGetPortApi;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
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
public class BotApiDelegateImpl implements BotApiDelegate {
    private final BotRegistrationPortAPI botRegistrationPortAPI;
    private final BotGetAllPortAPI botGetAllPortAPI;
    private final BotGetPortApi botGetPortApi;
    private final BotMapperAPI botMapperPortAPI;

    @Override
    public ResponseEntity<BotSummaryDTO> botBotIdGet(String botId) {
        return BotApiDelegate.super.botBotIdGet(botId);
    }

    @Override
    public ResponseEntity<Void> botBotIdPut(String botId, BotDTO botDTO) {
        return BotApiDelegate.super.botBotIdPut(botId, botDTO);
    }

    @Override
    public ResponseEntity<List<BotSummaryDTO>> botGet() {
        return BotApiDelegate.super.botGet();
    }

    @Override
    public ResponseEntity<Void> botPost(BotDTO botDTO) {
        return BotApiDelegate.super.botPost(botDTO);
    }
}
