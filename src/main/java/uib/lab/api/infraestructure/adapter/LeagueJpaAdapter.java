package uib.lab.api.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.jpaRepositories.LeagueJpaRepository;


@Component
public class LeagueJpaAdapter implements LeaguePort {

    private final LeagueJpaRepository leagueJpaRepository;
    private final LeagueMapper leagueMapper;
    private final UserMapper userMapper;

    public LeagueJpaAdapter(final LeagueJpaRepository leagueJpaRepository, final LeagueMapper leagueMapper, final UserMapper userMapper){
        this.leagueJpaRepository = leagueJpaRepository;
        this.leagueMapper = leagueMapper;
        this.userMapper 
        
        = userMapper;
    }

      @Override
    public Optional<LeagueDomain> findById(int id) {
        return leagueJpaRepository.findLeagueById(id).map(leagueMapper::toDomain);
    }

    @Override
    public List<LeagueDomain> findAllByUser(UserDomain userDomain){
          User user = userMapper.toEntity(userDomain);

        return leagueJpaRepository.findAllByUser(user)
                .stream()
                .map(leagueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public LeagueDomain save(LeagueDomain leagueDomain) {
        League leagueEntity = leagueMapper.toEntity(leagueDomain);
        League savedLeague = leagueJpaRepository.save(leagueEntity);
        System.out.println("Liga guardada con ID: " + savedLeague.getId());
        return leagueMapper.toDomain(savedLeague);
    }

    @Override
     public List<LeagueDomain> findAllLeagues() {
        return leagueJpaRepository.findAll()
                .stream()
                .map(leagueMapper::toDomain)
                .collect(Collectors.toList());
    }
}
