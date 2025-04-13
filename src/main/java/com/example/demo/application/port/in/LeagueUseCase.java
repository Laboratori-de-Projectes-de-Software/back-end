package com.example.demo.application.port.in;

import com.example.demo.dtos.LeagueDTO;
import com.example.demo.dtos.LeagueResponseDTO;
import com.example.demo.dtos.LeagueSummaryDTO;



import java.util.List;

/**
 * Define los casos de uso para la gestión de ligas.
 */
public interface LeagueUseCase {
    LeagueDTO createLeague(LeagueDTO leagueDTO);
    LeagueResponseDTO updateLeague(Long leagueId, LeagueDTO leagueDTO);
    LeagueDTO getLeague(Long leagueId);
    List<LeagueDTO> listLeagues();
    List<LeagueSummaryDTO> listLeagueSummaries(); // nuevo método

}
