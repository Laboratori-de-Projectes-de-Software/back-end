package com.debateia.adapter.in.web;
import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.domain.League;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * Handles creating matches, etc...
 * @author kjorda
 */

@RestController
@RequestMapping("/league")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueUseCase leagueUseCase;
    
    @GetMapping("/{id}")
    public League getLeague( // TODO: Should return LeagueDTO, not league.
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate,
            @PathVariable Integer id) {
        return leagueUseCase.getLeague(id);
    }
}