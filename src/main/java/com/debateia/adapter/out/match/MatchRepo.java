package com.debateia.adapter.out.match;

import com.debateia.adapter.mapper.MatchMapper;
import com.debateia.adapter.out.league.LeagueEntity;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchRepo implements MatchRepository {
    private final MatchJpaRepository matchJpaRepository;
    private final MatchMapper matchMapper;

    public Optional<Match> findById(Integer matchId) {
        return matchJpaRepository.findById(matchId).map(matchMapper::toDomain);
    }

    @Override
    public List<Match> findByLeagueId(Integer leagueId) {
        return matchJpaRepository.findByLeagueId(leagueId).stream().map(matchMapper::toDomain).toList();
    }

    @Override
    public List<Match> saveAll(List<Match> matches) {
        return matchJpaRepository.saveAll(matches.stream().map(matchMapper::toEntity).toList())
                .stream().map(matchMapper::toDomain).toList();
    }

    public Match save(Match match) {
        return matchMapper.toDomain(matchJpaRepository.save(matchMapper.toEntity(match)));
    }

    public Match updateMatch(Match match) {
        MatchEntity saved = matchJpaRepository.save(matchMapper.toEntity(match));
        return matchMapper.toDomain(saved);
    }
}
