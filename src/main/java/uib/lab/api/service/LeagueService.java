package uib.lab.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.entity.League;
import uib.lab.api.repository.LeagueRepository;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public League createLeague(League league) {
        return leagueRepository.save(league);
    }
}
