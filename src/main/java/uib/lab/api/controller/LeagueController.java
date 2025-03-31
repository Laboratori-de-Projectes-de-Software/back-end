package uib.lab.api.controller;

import java.util.Set;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import uib.lab.api.dto.League.LeagueRequest;
import uib.lab.api.entity.League;
import uib.lab.api.service.LeagueService;
import uib.lab.api.entity.LeagueState;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ligas")
@PreAuthorize("isAuthenticated()")
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<League> createLeague(@Valid @RequestBody LeagueRequest leagueRequest) {
        League league = new League();
        league.setName(leagueRequest.getName());
        league.setPlayTime(leagueRequest.getPlayTime());
        league.setNumRounds(leagueRequest.getNumRounds());
        league.setState(LeagueState.PENDING);

        League savedLeague = leagueService.createLeague(league);

        return new ResponseEntity<>(savedLeague, HttpStatus.CREATED);
    }
}
