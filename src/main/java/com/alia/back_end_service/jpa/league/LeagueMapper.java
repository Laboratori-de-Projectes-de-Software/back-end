package com.alia.back_end_service.jpa.league;


import com.alia.back_end_service.domain.league.League;

public interface LeagueMapper {
    League toDomain(LeagueEntity entity);
    LeagueEntity toEntity(League league);
}
