package com.example.demo.adapters.in.web;

import com.example.demo.application.port.in.LeagueUseCase;
import com.example.demo.domain.model.User;
import com.example.demo.dtos.LeagueDTO;
import com.example.demo.dtos.LeagueResponseDTO;
import com.example.demo.dtos.LeagueSummaryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/league")
public class LeagueController {

    private final LeagueUseCase leagueUseCase;

    public LeagueController(LeagueUseCase leagueUseCase) {
        this.leagueUseCase = leagueUseCase;
    }

    @PostMapping
    public ResponseEntity<LeagueResponseDTO> createLeague(@RequestBody LeagueDTO leagueDTO) {
        LeagueDTO created = leagueUseCase.createLeague(leagueDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        LeagueResponseDTO resp = new LeagueResponseDTO();
        resp.setLeagueId(created.getId());
        resp.setState("Active");
        resp.setName(created.getName());
        resp.setUrlImagen(created.getUrlImagen());
        resp.setUser(userId);
        resp.setRounds(created.getRounds());
        resp.setMatchTime(created.getMatchTime());
        resp.setBots(created.getBots());

        return ResponseEntity.ok(resp);
    }

    // GET /league/{id} → devuelve la info completa de una liga
    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> getLeague(@PathVariable Long leagueId) {
        LeagueDTO leagueDTO = leagueUseCase.getLeague(leagueId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        LeagueResponseDTO resp = new LeagueResponseDTO();
        resp.setLeagueId(leagueDTO.getId());
        resp.setState("Active"); // o lógica real si cambia
        resp.setName(leagueDTO.getName());
        resp.setUrlImagen(leagueDTO.getUrlImagen());
        resp.setUser(userId);
        resp.setRounds(leagueDTO.getRounds());
        resp.setMatchTime(leagueDTO.getMatchTime());
        resp.setBots(leagueDTO.getBots());

        return ResponseEntity.ok(resp);
    }

    // GET /league → devuelve solo nombre e imagen
    @GetMapping
    public ResponseEntity<List<LeagueSummaryDTO>> listLeagueSummaries() {
        List<LeagueSummaryDTO> summaries = leagueUseCase.listLeagueSummaries();
        return ResponseEntity.ok(summaries);
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> updateLeague(@PathVariable Long leagueId, @RequestBody LeagueDTO leagueDTO) {
        LeagueResponseDTO updated = leagueUseCase.updateLeague(leagueId, leagueDTO);
        return ResponseEntity.ok(updated);
    }
}
