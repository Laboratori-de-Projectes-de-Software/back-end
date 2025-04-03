package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.domain.entity.League;
import uib.lab.api.infraestructura.JpaRepositories.LeagueJpaRepository;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueJpaRepository leagueRepository;

    public League createLeague(League league) {
        return leagueRepository.save(league);
    }
}
