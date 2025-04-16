package com.alia.back_end_service.domain.classification.ports;

import com.alia.back_end_service.domain.classification.Classification;

import java.util.List;

public interface ClassificationPortDB {
    void save(Classification classification);
    List<Classification> findByLeague(Integer leagueId);
}
