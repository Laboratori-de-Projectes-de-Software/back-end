package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.User;
import java.util.List;
import java.util.Optional;

public interface LeagueJpaRepository extends JpaRepository<League, Integer> {
    List<League> findAllByUser(User user);
    Optional<League> findLeagueById(int id);
}
