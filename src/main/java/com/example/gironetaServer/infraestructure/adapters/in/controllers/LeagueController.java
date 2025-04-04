package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.application.services.LeagueService;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.TimeoutException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LigaDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.LigaMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0")
public class LeagueController {

    private final LeagueService leagueService;
    private final LigaMapper ligaMapper;

    public LeagueController(LeagueService leagueService, LigaMapper ligaMapper) {
        this.leagueService = leagueService;
        this.ligaMapper = ligaMapper;
    }

    @PostMapping("/league")
    public ResponseEntity<LigaDto> createLeague(@RequestBody LigaDto leagueDto) {
        try {
            League league = LigaMapper.toAppObject(leagueDto);
            League savedLeague = leagueService.createLeague(league);
            return ResponseEntity.status(HttpStatus.CREATED).body(ligaMapper.toLeagueDto(savedLeague));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/league")
    public ResponseEntity<List<LigaDto>> getAllLeagues(@RequestParam(required = false) Long owner) {
        try {
            List<League> leagues = leagueService.getAllLeagues(owner);
            List<LigaDto> leagueDtos = leagues.stream()
                    .map(ligaMapper::toLeagueDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(leagueDtos);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/league/{id}")
    public ResponseEntity<LigaDto> getLeagueById(@PathVariable Long id) {
        try {
            League league = leagueService.getLeagueById(id);
            LigaDto leagueDto = ligaMapper.toLeagueDto(league);
            return ResponseEntity.ok(leagueDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ForbiddenException | UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/league/{id}")
    public ResponseEntity<LigaDto> updateLeague(@PathVariable Long id, @RequestBody LigaDto leagueDto) {
        try {
            League league = LigaMapper.toAppObject(leagueDto);
            League updatedLeague = leagueService.updateLeague(id, league);
            LigaDto updatedLeagueDto = ligaMapper.toLeagueDto(updatedLeague);
            return ResponseEntity.ok(updatedLeagueDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ForbiddenException | UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/league/{id}/bot")
    public ResponseEntity<Void> registerBot(@PathVariable Long id, @RequestBody Map<String, Long> request) {
        try {
            leagueService.registerBotToLeague(id, request.get("botId"));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ForbiddenException | UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}