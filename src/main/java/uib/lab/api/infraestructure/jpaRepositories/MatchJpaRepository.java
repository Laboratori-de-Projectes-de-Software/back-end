package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.infraestructure.jpaEntity.Match;

public interface MatchJpaRepository extends JpaRepository<Match, Integer> {
}
