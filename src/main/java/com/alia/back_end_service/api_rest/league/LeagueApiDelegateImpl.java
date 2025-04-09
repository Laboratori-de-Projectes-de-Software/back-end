package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api.LeagueApiDelegate;
import com.alia.back_end_service.api_model.*;
import com.alia.back_end_service.api_rest.bot.BotMapperAPI;
import com.alia.back_end_service.api_rest.game.GameMapperAPI;
import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
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
    private final LeagueStartPortAPI leagueStartPortAPI;
    private final LeagueDeletePortAPI leagueDeletePortAPI;
    private final LeagueUpdatePortAPI leagueUpdatePortAPI;
    private final LeagueGetAllGamesPortAPI leagueGetAllGamesPortAPI;
    private final LeagueGetAllByUserPortAPI leagueGetAllByUserPortAPI;
    private final GameMapperAPI gameMapperAPI;


    @Override
    public ResponseEntity<LeagueResponseDTO> leagueLeagueIdDelete(Integer leagueId) {
        leagueDeletePortAPI.deleteLeague(leagueId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<LeagueResponseDTO> leagueLeagueIdGet(Integer leagueId) {
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
    public ResponseEntity<List<LeagueResponseDTO>> leagueGet(String owner) {
        List<League> leagues;

        if (owner != null && !owner.isBlank()) {
            leagues = leagueGetAllByUserPortAPI.getAllLeaguesByUser(owner);
        } else {
            leagues = leagueGetAllPortAPI.getAllLeagues();
        }

        List<LeagueResponseDTO> leaguesDTOs = leagues.stream()
                .map(leagueMapperAPI::toApiResponse)
                .toList();

        return ResponseEntity.ok(leaguesDTOs);
    }

    @Override
    public ResponseEntity<List<ClassificationResponseDTO>> leagueLeagueIdLeaderboardGet(Integer leagueId) {
        return LeagueApiDelegate.super.leagueLeagueIdLeaderboardGet(leagueId);
    }

    @Override
    public ResponseEntity<List<MatchResponseDTO>> leagueLeagueIdMatchGet(Integer leagueId) {
        List<Game> games = leagueGetAllGamesPortAPI.getAllLeagueGames(leagueId);
        List<MatchResponseDTO> matchesDTOs = new ArrayList<>();

        for (Game game : games) {
            matchesDTOs.add(gameMapperAPI.toApiResponse(game));
        }
        return ResponseEntity.ok(matchesDTOs);
    }

    @Override
    public ResponseEntity<Void> leagueLeagueIdStartPost(Integer leagueId) {
        leagueStartPortAPI.startLeague(leagueId);
        return ResponseEntity.status(HttpStatus.OK).build();
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
