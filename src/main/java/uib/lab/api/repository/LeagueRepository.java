package uib.lab.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.entity.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
