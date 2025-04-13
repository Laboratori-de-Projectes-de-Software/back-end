package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.MatchEntity;
import com.debateia.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Integer> {
    List<MatchEntity> findByLeagueId(Integer leagueId);
}
