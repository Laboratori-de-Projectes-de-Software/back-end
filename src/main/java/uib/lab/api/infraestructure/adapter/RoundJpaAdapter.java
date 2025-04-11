package uib.lab.api.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.infraestructure.jpaEntity.Round;
import uib.lab.api.infraestructure.jpaRepositories.RoundJpaRepository;

@Component
@RequiredArgsConstructor
public class RoundJpaAdapter implements RoundPort {

    private final RoundJpaRepository roundRepository;

    @Override
    public Round save(Round round) {
        return roundRepository.save(round);
    }
}
