package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.classification.Classification;
import com.alia.back_end_service.domain.classification.ports.ClassificationPortDB;
import com.alia.back_end_service.domain.league.ports.LeagueClassificationByLeaguePortAPI;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetClassificationsByLeagueUseCase implements LeagueClassificationByLeaguePortAPI {
    private final ClassificationPortDB classificationPortDB;
    @Override
    public List<Classification> findClassificationByLeague(Integer leagueId) {
        return classificationPortDB.findByLeague(leagueId);
    }
}
