package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IMatchRepository;
import jaumesitos.backend.demo.domain.Match;
import jaumesitos.backend.demo.infrastructure.db.mapper.MatchDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MatchDBORepository implements IMatchRepository {
    private final SpringDataMatchRepository springDataRepository;
    @Qualifier("matchDBOMapper")
    private final MatchDBOMapper mapper;

    @Override
    public void save(Match enfrentament) {
        //springDataRepository.save(mapper.toDBO(enfrentament));
    }

    @Override
    public Optional<Match> findById(String id) {
        //return springDataRepository.findById(id).map(mapper::toDomain);
        return null;
    }

    @Override
    public List<Match> findAll() {
        /*return springDataRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());*/
        return null;
    }

    @Override
    public List<Match> findByLeagueId(int leagueId) {
        return springDataRepository.findByLeagueId(leagueId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}