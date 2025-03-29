package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api.LeaguesApiDelegate;
import com.alia.back_end_service.api_model.*;
import com.alia.back_end_service.api_rest.bot.BotMapperAPI;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetPortApi;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class LeagueApiDelegateImpl implements LeaguesApiDelegate {

    private final LeagueCreatePortAPI leagueCreatePortAPI;
    private final LeagueGetPortAPI leagueGetPortAPI;
    private final LeagueGetAllPortAPI leagueGetAllPortAPI;
    private final LeagueMapperAPI leagueMapperAPI;
    private final LeagueInscribeBotPortAPI leagueInscribeBotPortAPI;
    private final BotMapperAPI botMapperAPI;
    private final LeagueGetAllBotsPortAPI leagueGetAllBotsPortAPI;


    @Override
    public ResponseEntity<List<LeagueResponse>> leaguesAllGet() {
        return ResponseEntity.status(HttpStatus.OK).body(leagueGetAllPortAPI.getAllLeagues()
                .stream()
                .map(leagueMapperAPI::toApiResponse)
                .toList());
    }

    @Override
    public ResponseEntity<List<BotReturn>> leaguesIdBotsGet(Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(leagueGetAllBotsPortAPI.getAllLeagueBots(id)
                .stream()
                .map(botMapperAPI::toApiResponse)
                .toList());
    }

    @Override
    public ResponseEntity<LeagueResponse> createLeague(@Valid LeagueCreate leagueCreate) {
        League rt = leagueCreatePortAPI.createLeague(leagueMapperAPI.toDomainCreate(leagueCreate));
        return new ResponseEntity<>(leagueMapperAPI.toApiResponse(rt), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> leaguesInscribeBotPost(LeagueInscribe leagueInscribe) {
        leagueInscribeBotPortAPI.inscribe(leagueInscribe.getLeagueId(),leagueInscribe.getBotId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<LeagueResponse> leaguesIdGet(Integer id) {
        return new ResponseEntity<>(leagueMapperAPI.toApiResponse(leagueGetPortAPI.getLeague(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> leaguesIdPatch(Integer id, LeagueUpdate leagueUpdate) {
        League aux = leagueGetPortAPI.getLeague(id);
        leagueCreatePortAPI.createLeague(leagueMapperAPI.toDomainUpdate(leagueUpdate,aux));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
