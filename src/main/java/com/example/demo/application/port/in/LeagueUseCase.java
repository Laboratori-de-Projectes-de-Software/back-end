package com.example.demo.application.port.in;

import java.util.List;

import com.example.demo.dtos.LeagueDTO;

/**
 * Define los casos de uso para la gestión de ligas.
 */
public interface LeagueUseCase {
    LeagueDTO createLeague(LeagueDTO leagueDTO);
    LeagueDTO updateLeague(Long leagueId, LeagueDTO leagueDTO);
    LeagueDTO getLeague(Long leagueId);
    List<LeagueDTO> listLeagues();
}
