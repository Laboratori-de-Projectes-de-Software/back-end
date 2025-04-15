package com.debateia.adapter.in.web;
import com.debateia.adapter.in.web.dto.request.LeagueDTO;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.in.web.dto.response.MatchResponseDTO;
import com.debateia.adapter.in.web.dto.response.ParticipationResponseDTO;
import com.debateia.application.jwt.JwtService;
import com.debateia.adapter.mapper.LeagueMapper;
import com.debateia.adapter.mapper.MatchMapper;
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
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;


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
    public ResponseEntity<List<LeagueResponseDTO>> getAllLeagues(
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        
        List<League> leagues;
        try {
            leagues = leagueUseCase.getAllLeagues(Optional.ofNullable(ownerId));
        }
        catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        List<LeagueResponseDTO> response = leagues.stream()
                .map(LeagueMapper::toLeagueResponseDTO)
                .toList(); // conversion a DTO muy prog funcional :3
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> getLeague(@PathVariable Integer id) {
        try {
            League lg = leagueUseCase.getLeague(id);
            return ResponseEntity.ok(LeagueMapper.toLeagueResponseDTO(lg));
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
        
        try {
            League newLeague = LeagueMapper.toDomain(league);
            newLeague.setUserId(userId);
            League updated = leagueUseCase.updateLeague(id, userId, newLeague);
            return ResponseEntity.ok(LeagueMapper.toLeagueResponseDTO(updated));
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (DataIntegrityViolationException e) { // El userId de la liga no es el del token
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
        catch (DataIntegrityViolationException e) { // El userId de la liga no es el del token
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @PostMapping("/{id}/start")
    public ResponseEntity<?> startLeague(@PathVariable Integer id) {
        try {
            leagueUseCase.startLeague(id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/match")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesLeague(@PathVariable Integer id) {
        //@TODO esto en la API pone que es en /league pero es un findMatchesByLeagueId, igual deberia ir en MatchController?
        // ante la duda lo programo en matchService, seria super facil cambiarlo
        List<Match> matches = matchService.getMatchesByLeagueId(id);
        return ResponseEntity.ok(matches.stream().map(MatchMapper::toResponseDTO).toList());
    }

}