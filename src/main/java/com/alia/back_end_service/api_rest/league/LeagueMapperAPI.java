package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api_model.LeagueCreate;
import com.alia.back_end_service.api_model.LeagueResponse;
import com.alia.back_end_service.api_model.LeagueUpdate;
import com.alia.back_end_service.domain.league.League;

public interface LeagueMapperAPI {
    League toDomainCreate(LeagueCreate leagueCreate);
    League toDomainUpdate(LeagueUpdate leagueUpdate, Integer id);
    LeagueResponse toApiResponse(League league);
}
