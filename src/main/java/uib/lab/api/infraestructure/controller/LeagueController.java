package uib.lab.api.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.service.LeagueService;
import uib.lab.api.infraestructure.util.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/league")
@PreAuthorize("isAuthenticated()")
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping
    public ApiResponse createLeague(@RequestBody LeagueDTO leagueDTO) {
        return leagueService.createLeague(leagueDTO);
    }
}
