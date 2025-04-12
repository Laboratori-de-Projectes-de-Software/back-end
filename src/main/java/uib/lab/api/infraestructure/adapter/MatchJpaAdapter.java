package uib.lab.api.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.MatchMapper;
import uib.lab.api.application.mapper.interfaces.RoundMapper;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaEntity.Round;
import uib.lab.api.infraestructure.jpaRepositories.MatchJpaRepository;
import uib.lab.api.infraestructure.jpaRepositories.RoundJpaRepository;


@Component
public class MatchJpaAdapter implements MatchPort {
    private final MatchJpaRepository matchJpaRepository;
    private final MatchMapper matchMapper;

    public MatchJpaAdapter(final MatchJpaRepository matchJpaRepository, final MatchMapper matchMapper){
        this.matchJpaRepository = matchJpaRepository;
        this.matchMapper = matchMapper;

    }
    @Override
    public Optional<MatchDomain> findById(int id) {
        return matchJpaRepository.findMatchById(id).map(matchMapper::toDomain);
    }

    @Override
    public List<MatchDomain> findAll(){
         return matchJpaRepository.findAll()
                .stream()
                .map(matchMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDomain> findAllByLeague(LeagueDomain league){
        return matchJpaRepository.findAllByRound_League_Id(league.getId())
        .stream()
        .map(matchMapper::toDomain)
        .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<MatchDomain> matches) {
        List<Match> matchEntities = matches.stream()
                .map(matchMapper::toEntity)
                .collect(Collectors.toList());

        matchJpaRepository.saveAll(matchEntities);
    }

}
