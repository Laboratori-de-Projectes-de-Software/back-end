package uib.lab.api.infraestructura.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import uib.lab.api.application.dto.League.LeagueRequest;
import uib.lab.api.domain.entity.League;
import uib.lab.api.application.service.LeagueService;
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
        league.setState(League.LeagueState.PENDING);

        League savedLeague = leagueService.createLeague(league);

        return new ResponseEntity<>(savedLeague, HttpStatus.CREATED);
    }
}
