package com.debateia.adapter.in.web;
import com.debateia.adapter.in.web.dto.request.LeagueDTO;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.in.web.dto.response.MatchResponseDTO;
import com.debateia.adapter.in.web.dto.response.ParticipationResponseDTO;
import com.debateia.application.jwt.JwtService;
import com.debateia.application.mapper.LeagueMapper;
import com.debateia.application.mapper.MatchMapper;
import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.application.service.MatchService;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpHeaders;

/**
 * Handles creating matches, etc...
 * @author kjorda
 */

@RestController
@RequestMapping("/league")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueUseCase leagueUseCase;
    private final MatchService matchService;
    private final JwtService jwtService;
   
    // TODO: TRY/CATCH
    
    @PostMapping
    public ResponseEntity<LeagueResponseDTO> postLeague(
            @RequestBody LeagueDTO league,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtService.extractUserId(authToken);
        
        League toCreate = LeagueMapper.toDomain(league);
        toCreate.setUserId(userId);
        
        League created = leagueUseCase.postLeague(toCreate);
        
        return ResponseEntity.ok(LeagueMapper.toLeagueResponseDTO(created));
    }
    
    @GetMapping
    public ResponseEntity<List<LeagueResponseDTO>> getAllLeagues() {
        return ResponseEntity.ok(null);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> getLeague(@PathVariable Integer id) {
        League lg = leagueUseCase.getLeague(id);
        return ResponseEntity.ok(LeagueMapper.toLeagueResponseDTO(lg));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> updateLeague(
            @PathVariable Integer id,
            @RequestBody LeagueDTO league,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtService.extractUserId(authToken);
        
        
        
        return ResponseEntity.ok(null);
    }
    
    @PostMapping("/{id}/bot")
    public ResponseEntity<?> registerBot(@RequestBody Integer botId) {
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/{id}/leaderboard")
    public ResponseEntity<List<ParticipationResponseDTO>> getScores(@PathVariable Integer id) {
        return ResponseEntity.ok(null);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> deleteLeague(
            @PathVariable Integer id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtService.extractUserId(authToken);
        
        try {
            League deleted = leagueUseCase.deleteLeague(id, userId);
            return ResponseEntity.ok(LeagueMapper.toLeagueResponseDTO(deleted));
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
    }
    
    @PostMapping("/{id}/start")
    public ResponseEntity<?> startLeague(@PathVariable Integer id) {
        leagueUseCase.startLeague(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/match")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesLeague(@PathVariable Integer id) {
        //@TODO esto en la API pone que es en /league pero es un findMatchesByLeagueId, igual deberia ir en MatchController?
        // ante la duda lo programo en matchService, seria super facil cambiarlo
        List<Match> matches = matchService.getMatchesByLeagueId(id);
        return ResponseEntity.ok(matches.stream().map(MatchMapper::toResponseDTO).toList());
    }

}