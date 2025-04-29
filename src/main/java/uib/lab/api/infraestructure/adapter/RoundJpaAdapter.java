package uib.lab.api.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.RoundMapper;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.domain.RoundDomain;
import uib.lab.api.infraestructure.jpaEntity.Round;
import uib.lab.api.infraestructure.jpaRepositories.RoundJpaRepository;


@Component
public class RoundJpaAdapter implements RoundPort{
    
    private final RoundJpaRepository roundJpaRepository;
    private final RoundMapper roundMapper;

    public RoundJpaAdapter(final RoundJpaRepository roundJpaRepository, final RoundMapper roundMapper){
        this.roundJpaRepository = roundJpaRepository;
        this.roundMapper = roundMapper;
    }

    @Override
    public Optional<RoundDomain> findById(int id){
        return roundJpaRepository.findRoundById(id).map(roundMapper::toDomain);
   
    }

    @Override
    public List<RoundDomain> saveAll(List<RoundDomain> rounds) {
        return roundJpaRepository.saveAll(
                        rounds.stream()
                                .map(roundMapper::toEntity)
                                .collect(Collectors.toList())
                ).stream()
                .map(roundMapper::toDomain)
                .collect(Collectors.toList());
    }

    public void deleteAll(List<RoundDomain> rounds) {
        roundJpaRepository.deleteAll(
                rounds.stream().map(roundMapper::toEntity).collect(Collectors.toList())
        );
    }

    @Override
    public List<RoundDomain> findAllByLeagueId(int leagueId) {
        return roundJpaRepository.findAllByLeagueId(leagueId)
                .stream().map(roundMapper::toDomain)
                .collect(Collectors.toList());
    }
}