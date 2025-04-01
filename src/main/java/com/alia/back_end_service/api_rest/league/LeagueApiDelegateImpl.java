package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api.LeaguesApiDelegate;
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

import java.util.List;

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
    private final LeagueUpdatePortAPI leagueUpdatePortAPI;


    @Override
    public ResponseEntity<LeagueDTO> leaguesLeagueIdDelete(Integer leagueId) {
        return LeaguesApiDelegate.super.leaguesLeagueIdDelete(leagueId);
    }

    @Override
    public ResponseEntity<LeagueDTO> leaguesLeagueIdGet(Integer leagueId) {
        return LeaguesApiDelegate.super.leaguesLeagueIdGet(leagueId);
    }

    @Override
    public ResponseEntity<Void> leaguesLeagueIdPut(Integer leagueId, LeagueDTO leagueDTO) {
        return LeaguesApiDelegate.super.leaguesLeagueIdPut(leagueId, leagueDTO);
    }

}
