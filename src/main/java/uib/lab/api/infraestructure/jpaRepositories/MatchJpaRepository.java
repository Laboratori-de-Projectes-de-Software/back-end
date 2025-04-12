package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Match;
import java.util.List;
import java.util.Optional;

public interface MatchJpaRepository extends JpaRepository<Match, Integer>{
    Optional<Match> findMatchById(int id);
}

