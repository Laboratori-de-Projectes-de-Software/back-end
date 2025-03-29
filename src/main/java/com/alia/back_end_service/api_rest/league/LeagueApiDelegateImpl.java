package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api.LeaguesApiDelegate;
import com.alia.back_end_service.api_model.BotReturn;
import com.alia.back_end_service.api_model.LeagueCreate;
import com.alia.back_end_service.api_model.LeagueResponse;
import com.alia.back_end_service.api_model.UserResponse;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeagueCreatePortAPI;
import com.alia.back_end_service.domain.league.ports.LeagueGetAllPortAPI;
import com.alia.back_end_service.domain.league.ports.LeagueGetPortAPI;
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


    @Override
    public ResponseEntity<List<LeagueResponse>> leaguesAllGet() {
        List<League> leagues = leagueGetAllPortAPI.getAllLeagues();
        List<LeagueResponse> leagueResponses = new ArrayList<>();
        LeagueResponse leagueResponse;
        for (League league : leagues) {
             leagueResponse = new LeagueResponse();
             leagueResponse.setId(league.getId());
             leagueResponse.setName(league.getName());
             leagueResponse.setInitTime(league.getInit_time());
             leagueResponse.setEndTime(league.getEnd_time());
             leagueResponse.setTimeMatch(league.getTime_match());
             leagueResponse.setNumberMatch(league.getNumber_match());
             leagueResponse.setState(league.getState());
             leagueResponses.add(leagueResponse);
        }
        return new ResponseEntity<>(leagueResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LeagueResponse> leaguesCreatePost(LeagueCreate leagueCreate) {
        League league = new League();
        league.setName(leagueCreate.getName());
        league.setNumber_match(leagueCreate.getNumberMatch());
        league.setTime_match(leagueCreate.getTimeMatch());
        league.setInit_time(OffsetDateTime.now());
        league.setState("Espera"); // Crear enum

        League rt = leagueCreatePortAPI.createLeague(league);
        LeagueResponse leagueResponse = new LeagueResponse();
        leagueResponse.setId(rt.getId());
        leagueResponse.setName(rt.getName());
        leagueResponse.setInitTime(rt.getInit_time());
        leagueResponse.setEndTime(rt.getEnd_time());
        leagueResponse.setNumberMatch(league.getNumber_match());
        leagueResponse.setTimeMatch(league.getTime_match());
        leagueResponse.setState(rt.getState());

        return new ResponseEntity<>(leagueResponse, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<LeagueResponse> leaguesIdGet(Integer id) {
        League league = leagueGetPortAPI.getLeague(id);
        LeagueResponse leagueResponse = new LeagueResponse();
        leagueResponse.setId(league.getId());
        leagueResponse.setName(league.getName());
        leagueResponse.setInitTime(league.getInit_time());
        leagueResponse.setEndTime(league.getEnd_time());
        leagueResponse.setNumberMatch(league.getNumber_match());
        leagueResponse.setTimeMatch(league.getTime_match());
        leagueResponse.setState(league.getState());
        return new ResponseEntity<>(leagueResponse, HttpStatus.OK);
    }


}
