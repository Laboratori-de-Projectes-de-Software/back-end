package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api.BotApiDelegate;
import com.alia.back_end_service.api_model.BotDTO;
import com.alia.back_end_service.api_model.BotSummaryDTO;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetAllPortAPI;
import com.alia.back_end_service.domain.bot.port.BotGetPortApi;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import com.alia.back_end_service.domain.bot.port.BotUpdatePortAPI;
import com.alia.back_end_service.domain.league.ports.LeagueGetPortAPI;
import com.alia.back_end_service.domain.user.ports.GetAllUserBotsPortAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BotApiDelegateImpl implements BotApiDelegate {
    private final BotRegistrationPortAPI botRegistrationPortAPI;
    private final BotGetAllPortAPI botGetAllPortAPI;
    private final BotGetPortApi botGetPortApi;
    private final GetAllUserBotsPortAPI getAllUserBotsPortAPI;
    private final BotUpdatePortAPI botUpdatePortAPI;
    private final BotMapperAPI botMapperPortAPI;

    @Override
    public ResponseEntity<BotSummaryDTO> botBotIdGet(Integer botId) {
        return ResponseEntity.status(HttpStatus.OK).body(botMapperPortAPI.toApiResponseSummary(botGetPortApi.findById(botId)));
    }

    @Override
    public ResponseEntity<Void> botBotIdPut(Integer botId, BotDTO botDTO) {
        botUpdatePortAPI.update(botMapperPortAPI.toDomainRegister(botDTO),botId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<BotSummaryDTO>> botGet() {
        return ResponseEntity.status(HttpStatus.OK).body(botGetAllPortAPI.getAllBots().stream().map(botMapperPortAPI::toApiResponseSummary).toList());
    }

    @Override
    public ResponseEntity<List<BotSummaryDTO>> botOwneruserIdGet(String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(getAllUserBotsPortAPI.getAllUserBots(userId).stream().map(botMapperPortAPI::toApiResponseSummary).toList());
    }

    @Override
    public ResponseEntity<Void> botPost(BotDTO botDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Bot bot = botMapperPortAPI.toDomainRegister(botDTO);
        bot.setUserId(username);
        botRegistrationPortAPI.registerBot(bot); // No he puesto devolver el bot porque no lo han definido los compañeros :)
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
