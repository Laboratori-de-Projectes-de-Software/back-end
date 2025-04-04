package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api.LeagueApiDelegate;
import com.alia.back_end_service.api_model.*;
import com.alia.back_end_service.api_rest.bot.BotMapperAPI;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LeagueApiDelegateImpl implements LeagueApiDelegate {

    private final LeagueCreatePortAPI leagueCreatePortAPI;
    private final LeagueGetPortAPI leagueGetPortAPI;
    private final LeagueGetAllPortAPI leagueGetAllPortAPI;
    private final LeagueMapperAPI leagueMapperAPI;
    private final LeagueInscribeBotPortAPI leagueInscribeBotPortAPI;
    private final BotMapperAPI botMapperAPI;
    private final LeagueGetAllBotsPortAPI leagueGetAllBotsPortAPI;
    private final LeagueUpdatePortAPI leagueUpdatePortAPI;


    @Override
    public ResponseEntity<LeagueDTO> leagueLeagueIdDelete(Integer leagueId) {
        return LeagueApiDelegate.super.leagueLeagueIdDelete(leagueId);
    }

    @Override
    public ResponseEntity<LeagueDTO> leagueLeagueIdGet(Integer leagueId) {
        return ResponseEntity.ok(leagueMapperAPI.toApiResponse(leagueGetPortAPI.getLeague(leagueId)));
    }

    @Override
    public ResponseEntity<Void> leagueLeagueIdPut(Integer leagueId, LeagueDTO leagueDTO) {
        League league = leagueMapperAPI.toDomainCreate(leagueDTO);
        league.setId(leagueId);
        leagueUpdatePortAPI.updateLeague(league);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Override
    public ResponseEntity<List<LeagueDTO>> leagueGet() {
        List<League> leagues = leagueGetAllPortAPI.getAllLeagues();
        List<LeagueDTO> leaguesDTOs = new ArrayList<>();

        for (League league : leagues) {
            leaguesDTOs.add(leagueMapperAPI.toApiResponse(league));
        }

        return ResponseEntity.ok(leaguesDTOs);
    }

    @Override
    public ResponseEntity<List<ParticipationDTO>> leagueLeagueIdLeaderboardGet(Integer leagueId) {
        return LeagueApiDelegate.super.leagueLeagueIdLeaderboardGet(leagueId);
    }

    @Override
    public ResponseEntity<List<MatchDTO>> leagueLeagueIdMatchGet(Integer leagueId) {
        return LeagueApiDelegate.super.leagueLeagueIdMatchGet(leagueId);
    }

    @Override
    public ResponseEntity<Void> leagueLeagueIdStartPost(Integer leagueId) {
        return LeagueApiDelegate.super.leagueLeagueIdStartPost(leagueId);
    }

    @Override
    public ResponseEntity<List<LeagueDTO>> leagueOwneruserIdGet(String userId) {
        return LeagueApiDelegate.super.leagueOwneruserIdGet(userId);
    }

    @Override
    public ResponseEntity<Void> leaguePost(LeagueDTO leagueDTO) {
        leagueCreatePortAPI.createLeague(leagueMapperAPI.toDomainCreate(leagueDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> leagueLeagueIdBotPost(Integer leagueId, LeagueLeagueIdBotPostRequest leagueLeagueIdBotPostRequest) {
        leagueInscribeBotPortAPI.inscribe(leagueId, leagueLeagueIdBotPostRequest.getBotId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
