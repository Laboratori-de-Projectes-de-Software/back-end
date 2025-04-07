package com.example.demo.adapters.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.port.in.LeagueUseCase;
import com.example.demo.dtos.LeagueDTO;

/**
 * Controlador REST que expone los endpoints para gestionar ligas.
 */
@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeagueUseCase leagueUseCase;

    public LeagueController(LeagueUseCase leagueUseCase) {
        this.leagueUseCase = leagueUseCase;
    }

    @PostMapping
    public ResponseEntity<LeagueDTO> createLeague(@RequestBody LeagueDTO leagueDTO) {
        LeagueDTO created = leagueUseCase.createLeague(leagueDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> updateLeague(@PathVariable Long leagueId,
                                                    @RequestBody LeagueDTO leagueDTO) {
        LeagueDTO updated = leagueUseCase.updateLeague(leagueId, leagueDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> getLeague(@PathVariable Long leagueId) {
        LeagueDTO league = leagueUseCase.getLeague(leagueId);
        return ResponseEntity.ok(league);
    }

    @GetMapping
    public ResponseEntity<List<LeagueDTO>> listLeagues() {
        List<LeagueDTO> leagues = leagueUseCase.listLeagues();
        return ResponseEntity.ok(leagues);
    }
}
