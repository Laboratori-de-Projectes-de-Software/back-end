package uib.lab.api.infraestructure.adapter;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.mapper.interfaces.RoundMapper;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.RoundDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.jpaRepositories.LeagueJpaRepository;
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
}
