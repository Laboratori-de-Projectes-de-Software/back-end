package com.debateia.adapter.out.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Integer> {
    List<MatchEntity> findByLeagueId(Integer leagueId);
}
