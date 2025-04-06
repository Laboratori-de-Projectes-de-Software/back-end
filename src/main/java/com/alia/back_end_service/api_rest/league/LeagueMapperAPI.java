package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api_model.LeagueDTO;
import com.alia.back_end_service.api_model.LeagueResponseDTO;
import com.alia.back_end_service.domain.league.League;

public interface LeagueMapperAPI {
    League toDomainCreate(LeagueDTO leagueCreate);
    LeagueResponseDTO toApiResponse(League league);
}
