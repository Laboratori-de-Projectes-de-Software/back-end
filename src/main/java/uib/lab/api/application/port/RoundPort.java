package uib.lab.api.application.port;


import java.util.List;
import uib.lab.api.domain.RoundDomain;
import java.util.Optional;

public interface RoundPort {
    Optional<RoundDomain> findById(int id);
    
}
