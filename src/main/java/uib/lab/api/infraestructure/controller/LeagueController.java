package uib.lab.api.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.user.UserResponseDTO;
import uib.lab.api.application.service.LeagueService;
import uib.lab.api.infraestructure.util.ApiResponse;
import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/league")
//@PreAuthorize("isAuthenticated()")
public class LeagueController {

    private final LeagueService leagueService;

    @Transactional
    @PostMapping
    public ApiResponse createLeague(@RequestBody LeagueDTO leagueDTO) {
        return leagueService.createLeague(leagueDTO);
    }

    @GetMapping
    public ApiResponse getAllLeagues(@RequestParam(required = false) Integer owner) {
        return leagueService.getAllLeagues(owner);
    }

    @GetMapping("/{leagueId}")
    public ApiResponse getLeagueById(@PathVariable int leagueId) {
        return leagueService.getLeagueById(leagueId);
    }
}
