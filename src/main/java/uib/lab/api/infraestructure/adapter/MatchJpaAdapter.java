package uib.lab.api.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaRepositories.MatchJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchJpaAdapter implements MatchPort {

    private final MatchJpaRepository matchRepository;

    @Override
    public Optional<Match> findById(int id) {
        return matchRepository.findById(id);
    }

    @Override
    public List<Match> saveAll(List<Match> matches) {
        return matchRepository.saveAll(matches);
    }
}
