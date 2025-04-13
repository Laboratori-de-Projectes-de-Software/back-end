package uib.lab.api.application.mapper.implementations;

import lombok.RequiredArgsConstructor;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.mapper.interfaces.RoundMapper;

import org.springframework.stereotype.Component;
import uib.lab.api.application.port.LeaguePort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.RoundDomain;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaEntity.Round;
import uib.lab.api.infraestructure.jpaRepositories.LeagueJpaRepository;
import uib.lab.api.infraestructure.jpaRepositories.MatchJpaRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoundMapperImpl implements RoundMapper{

    private final LeaguePort leaguePort;
    private final LeagueMapper leagueMapper;
    private final MatchJpaRepository matchJpaRepository;
    private final LeagueJpaRepository leagueRepository;


    @Override
    public RoundDomain toDomain(Round entity){
        if(entity == null){
            return null;
        }

        int[] matchsId = entity.getMatches() != null
                ? entity.getMatches().stream().mapToInt(Match::getId).toArray()
                : new int[0];
        
        return new RoundDomain(
            entity.getId(),
            entity.getInitialDate().toString(),
            entity.getLeague().getId(),
            matchsId
        );
    }


    @Override
    public Round toEntity(RoundDomain domain){
        Round entity = new Round();
        entity.setId(domain.getId());
        entity.setInitialDate(LocalDateTime.parse(domain.getInitialDate()));

        League league = leagueRepository.findById(domain.getLeagueId())
                .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + domain.getLeagueId()));
        entity.setLeague(league);


        if (domain.getMatchesId() != null) {
            Set<Match> matchs = Arrays.stream(domain.getMatchesId())
                    .mapToObj(id -> matchJpaRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Match not found: " + id)))
                    .collect(Collectors.toSet());
            entity.setMatches(matchs);
        } else {
            entity.setMatches(new HashSet<>());
        }

        return entity;
    }
}
