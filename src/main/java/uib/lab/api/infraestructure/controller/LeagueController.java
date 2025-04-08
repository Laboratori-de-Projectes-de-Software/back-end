package uib.lab.api.infraestructure.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.service.LeagueService;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/league")
@PreAuthorize("isAuthenticated()")
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping
    public Object createLeague(@RequestBody LeagueDTO leagueDTO, Locale locale) {
        return leagueService.createLeague(leagueDTO, locale);
    }
}
