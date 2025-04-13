package uib.lab.api.infraestructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.dto.user.UserResponseDTO;
import uib.lab.api.application.service.LeagueService;
import uib.lab.api.application.service.MatchService;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.application.service.MatchService;
import uib.lab.api.infraestructure.util.ApiResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/league")
@PreAuthorize("isAuthenticated()")
public class LeagueController {

    private final LeagueService leagueService;
    private final MatchService matchService;

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

    @PutMapping("/{leagueId}")
    public ApiResponse updateLeague(@PathVariable int leagueId, @Valid @RequestBody LeagueDTO leagueDTO) {
        return leagueService.updateLeague(leagueId, leagueDTO);
    }

    @GetMapping("/{leagueId}/match")
    public ApiResponse getMatchesByLeague(@PathVariable int leagueId){
        return matchService.getMatchesByLeague(leagueId);
    }

    @PostMapping("/{leagueId}/start")
    public ApiResponse startLeague(@PathVariable int leagueId) {
        return leagueService.startLeague(leagueId);
    }

    @DeleteMapping("/{leagueId}")
    public ApiResponse deleteLeague(@PathVariable int leagueId) {
        return leagueService.deleteLeague(leagueId);
    }
}