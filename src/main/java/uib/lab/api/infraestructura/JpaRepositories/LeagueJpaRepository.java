package uib.lab.api.infraestructura.JpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.domain.entity.League;

public interface LeagueJpaRepository extends JpaRepository<League, Long> {
}
