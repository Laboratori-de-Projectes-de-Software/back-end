package uib.lab.api.infraestructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.mapper.interfaces.MatchMapper;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.jpaRepositories.LeagueJpaRepository;
import uib.lab.api.infraestructure.jpaRepositories.MatchJpaRepository;


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
}
