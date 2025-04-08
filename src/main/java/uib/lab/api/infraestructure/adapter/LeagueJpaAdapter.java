package uib.lab.api.infraestructure.adapter;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaRepositories.LeagueJpaRepository;

@Component
public class LeagueJpaAdapter implements LeaguePort {

    private final LeagueJpaRepository leagueJpaRepository;
    private final LeagueMapper leagueMapper;

    public LeagueJpaAdapter(final LeagueJpaRepository leagueJpaRepository, final LeagueMapper leagueMapper){
        this.leagueJpaRepository = leagueJpaRepository;
        this.leagueMapper = leagueMapper;
    }

    @Override
    public LeagueDomain save(LeagueDomain leagueDomain) {
        League leagueEntity = leagueMapper.toEntity(leagueDomain);
        League savedLeague = leagueJpaRepository.save(leagueEntity);
        System.out.println("Liga guardada con ID: " + savedLeague.getId());
        return leagueMapper.toDomain(savedLeague);
    }
}
