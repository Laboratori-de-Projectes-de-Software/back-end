package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.classification.Classification;

import java.util.List;

public interface LeagueClassificationByLeaguePortAPI {
    List<Classification> findClassificationByLeague(Integer leagueId);
}
