package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.User;
import java.util.List;

public interface LeagueJpaRepository extends JpaRepository<League, Integer> {
    List<League> findAllByUser(User user);
}
