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

    @Override
    public Optional<Match> findById(Integer id) {
        return matchJpaRepository.findById(id).map(matchMapper::toDomain);
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

    @Override
    public Match updateMatch(Integer id, Match match) {
        MatchEntity toSave = matchMapper.toEntity(match);

        toSave.setId(id);
        MatchEntity saved = matchJpaRepository.save(toSave);

        return matchMapper.toDomain(saved);
    }
}
