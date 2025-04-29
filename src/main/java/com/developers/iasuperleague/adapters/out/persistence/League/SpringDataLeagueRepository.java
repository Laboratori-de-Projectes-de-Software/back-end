package com.developers.iasuperleague.adapters.out.persistence.League;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Spring Data que proporciona métodos CRUD para LeagueEntity.
 */
@Repository
public interface SpringDataLeagueRepository extends JpaRepository<LeagueEntity, Long> {
    // Aquí puedes definir métodos personalizados si los necesitas.
}
