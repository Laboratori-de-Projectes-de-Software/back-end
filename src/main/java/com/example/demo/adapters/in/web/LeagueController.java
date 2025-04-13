package com.example.demo.adapters.in.web;

import com.example.demo.application.port.in.LeagueUseCase;
import com.example.demo.domain.model.User;
import com.example.demo.dtos.LeagueDTO;
import com.example.demo.dtos.LeagueResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para gestionar ligas.
 */
@RestController
@RequestMapping("/league")
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

//    @PutMapping("/{leagueId}")
//    public ResponseEntity<LeagueDTO> updateLeague(@PathVariable Long leagueId,
//                                                    @RequestBody LeagueDTO leagueDTO) {
//        LeagueDTO updated = leagueUseCase.updateLeague(leagueId, leagueDTO);
//        return ResponseEntity.ok(updated);
//    }

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
