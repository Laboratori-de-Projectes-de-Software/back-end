package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.application.services.LeagueService;
import com.example.gironetaServer.domain.League;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LigaDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.LigaMapper;

@RestController
@RequestMapping("/api/v0")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping("/league")
    public ResponseEntity<LigaDto> createLeague(@RequestBody LigaDto leagueDto) {
        League league = LigaMapper.toAppObject(leagueDto);
        League savedLeague = leagueService.createLeague(league);
        System.out.println(savedLeague.getUserId());
        return ResponseEntity.ok(LigaMapper.toLeagueDto(savedLeague));
    }
}