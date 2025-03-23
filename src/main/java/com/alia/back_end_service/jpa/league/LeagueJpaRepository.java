package com.alia.back_end_service.jpa.league;

import com.alia.back_end_service.domain.league.League;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LeagueJpaRepository extends JpaRepository<LeagueEntity, Long> {

    @EntityGraph(attributePaths = {"bots"}) // Sin rounds de momento
    Optional<LeagueEntity> findById(Long id); // Detalles completos

    @Query("SELECT l FROM LeagueEntity l JOIN l.bots b WHERE b.user.username = :username")
    List<LeagueEntity> findLeaguesByUser(String username); // Ligas con bots del usuario
}
