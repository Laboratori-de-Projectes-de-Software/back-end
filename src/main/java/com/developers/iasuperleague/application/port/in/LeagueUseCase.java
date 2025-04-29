package com.developers.iasuperleague.application.port.in;




import com.developers.iasuperleague.dtos.LeagueDTO;
import com.developers.iasuperleague.dtos.LeagueResponseDTO;
import com.developers.iasuperleague.dtos.LeagueSummaryDTO;

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
