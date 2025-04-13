package com.debateia.adapter.in.web;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.in.web.dto.response.MatchResponseDTO;
import com.debateia.adapter.mapper.LeagueMapper;
import com.debateia.application.mapper.MatchMapper;
import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.application.service.MatchService;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles creating matches, etc...
 * @author kjorda
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueUseCase leagueUseCase;
    private final LeagueMapper leagueMapper;
    private final MatchService matchService;

    @GetMapping("/league/{id}")
    public LeagueResponseDTO getLeague(@PathVariable Integer id) {
        League lg = leagueUseCase.getLeague(id);
        return leagueMapper.toLeagueResponseDTO(lg);
    }

    @PostMapping("/league/{id}/start")
    public ResponseEntity<?> startLeague(@PathVariable Integer id) {
        leagueUseCase.startLeague(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/league/{id}/match")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesLeague(@PathVariable Integer id) {
        //@TODO esto en la API pone que es en /league pero es un findMatchesByLeagueId, igual deberia ir en MatchController?
        // ante la duda lo programo en matchService, seria super facil cambiarlo
        List<Match> matches = matchService.getMatchesByLeagueId(id);
        return ResponseEntity.ok(matches.stream().map(MatchMapper::toResponseDTO).toList());
    }

}