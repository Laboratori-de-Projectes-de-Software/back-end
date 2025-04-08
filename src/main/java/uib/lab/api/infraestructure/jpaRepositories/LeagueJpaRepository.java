package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.infraestructure.jpaEntity.League;

public interface LeagueJpaRepository extends JpaRepository<League, Integer> {
}
