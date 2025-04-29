package uib.lab.api.infraestructure.jpaRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.infraestructure.jpaEntity.Round;

import java.util.List;
import java.util.Optional;

public interface RoundJpaRepository extends JpaRepository<Round, Integer>{
    Optional<Round> findRoundById(int id);

    List<Round> findAllByLeagueId(int leagueId);
}
