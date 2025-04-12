package uib.lab.api.infraestructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.RoundMapper;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.domain.RoundDomain;
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
