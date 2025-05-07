package com.debateia.adapter.out.match;

import com.debateia.adapter.out.match.MatchJpaRepository;
import com.debateia.adapter.mapper.MatchMapper;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchRepo implements MatchRepository {
    private final MatchJpaRepository matchJpaRepository;
    private final MatchMapper matchMapper;

    @Override
    public List<Match> findByLeagueId(Integer leagueId) {
        return matchJpaRepository.findByLeagueId(leagueId).stream().map(matchMapper::toDomain).toList();
    }

    @Override
    public List<Match> saveAll(List<Match> matches) {
        return matchJpaRepository.saveAll(matches.stream().map(matchMapper::toEntity).toList())
                .stream().map(matchMapper::toDomain).toList();
    }

}
