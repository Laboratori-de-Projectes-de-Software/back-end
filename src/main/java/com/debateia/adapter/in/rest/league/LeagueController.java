package com.debateia.adapter.in.rest.league;
import com.debateia.adapter.in.rest.match.MatchDTO;
import com.debateia.adapter.mapper.LeagueMapper;
import com.debateia.adapter.mapper.MatchMapper;
import com.debateia.adapter.mapper.PartMapper;
import com.debateia.application.ports.in.rest.JWTUseCase;
import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.application.ports.in.rest.MatchUseCase;
import com.debateia.domain.League;
import com.debateia.domain.Match;
import com.debateia.domain.Participation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;


@RestController
@RequestMapping("/league")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueUseCase leagueUseCase;
    private final MatchUseCase matchUseCase;
    private final JWTUseCase jwtUseCase;
    private final LeagueMapper leagueMapper;
    private final PartMapper partMapper;
    private final MatchMapper matchMapper;
    
    @PostMapping
    public ResponseEntity<LeagueDTO> postLeague(
            @RequestBody CreateLeagueDTO league,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtUseCase.extractUserId(authToken);
        
        try {
            League toCreate = leagueMapper.toDomain(league);
            toCreate.setUserId(userId);
            League created = leagueUseCase.postLeague(toCreate);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(leagueMapper.toLeagueResponseDTO(created));
        }
        catch (DataIntegrityViolationException e) { // Alguno de los bots no existe
//            System.err.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }
    
    @GetMapping
    public ResponseEntity<List<LeagueDTO>> getAllLeagues(
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        
        List<League> leagues;
        try {
            leagues = leagueUseCase.getAllLeagues(Optional.ofNullable(ownerId));
        }
        catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        List<LeagueDTO> response = leagues.stream()
                .map(leagueMapper::toLeagueResponseDTO)
                .toList(); // conversion a DTO muy prog funcional :3
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> getLeague(@PathVariable Integer leagueId) {
        try {
            League lg = leagueUseCase.getLeague(leagueId);
            return ResponseEntity.ok(leagueMapper.toLeagueResponseDTO(lg));
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> updateLeague(
            @PathVariable Integer leagueId,
            @RequestBody CreateLeagueDTO league,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        
        final String authToken = authentication.substring(7);
        final Integer userId = jwtUseCase.extractUserId(authToken);
        
        try {
            League newLeague = leagueMapper.toDomain(league);
            newLeague.setUserId(userId);
            League updated = leagueUseCase.updateLeague(leagueId, userId, newLeague);
            return ResponseEntity.ok(leagueMapper.toLeagueResponseDTO(updated));
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping("/{leagueId}/bot")
    public ResponseEntity<?> registerBot(
            @PathVariable Integer leagueId,
            @RequestBody Integer botId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtUseCase.extractUserId(authToken);
        
        try {
            leagueUseCase.registerBot(leagueId, botId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (EntityNotFoundException e) { // El userId de la liga no es el del token
            System.err.println(e.getMessage());
//            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (DataIntegrityViolationException e) { // El userId de la liga no es el del token
            e.printStackTrace();
//            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // Convertimos l
    @GetMapping("/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipationDTO>> getScores(@PathVariable Integer leagueId) {
        try {
            List<Participation> scores = leagueUseCase.getScores(leagueId);
            
            // Convertimos lista de participaciones a lista de ParticipationResponseDTO
            List<ParticipationDTO> response = scores.stream()
                .map(elem -> partMapper.toDTO(elem))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(response);
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> deleteLeague(
            @PathVariable Integer leagueId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtUseCase.extractUserId(authToken);
        
        try {
            League deleted = leagueUseCase.deleteLeague(leagueId, userId);
            return ResponseEntity.ok(leagueMapper.toLeagueResponseDTO(deleted));
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping("/{leagueId}/start")
    public ResponseEntity<?> startLeague(@PathVariable Integer leagueId) {
        try {
            leagueUseCase.startLeague(leagueId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{leagueId}/match")
    public ResponseEntity<List<MatchDTO>> getMatchesLeague(@PathVariable Integer leagueId) {
        //@TODO esto en la API pone que es en /league pero es un findMatchesByLeagueId, igual deberia ir en MatchController?
        // ante la duda lo programo en matchService, seria super facil cambiarlo
        try {
            List<Match> matches = matchUseCase.getMatchesByLeagueId(leagueId);
            return ResponseEntity.ok(matches.stream().map(matchMapper::toResponseDTO).toList());
        } catch (EntityNotFoundException e) { // liga no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}