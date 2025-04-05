package com.alia.back_end_service.jpa.league;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueJpaRepository extends JpaRepository<LeagueEntity, Integer> {
    @EntityGraph(attributePaths = {"bots"})
    List<LeagueEntity> findAll();

    @EntityGraph(attributePaths = {"bots"})
    Optional<LeagueEntity> findById(Integer id);

    @Query("SELECT l FROM LeagueEntity l JOIN l.bots b WHERE b.user.username = :username")
    List<LeagueEntity> findLeaguesByUser(String username); // Ligas con bots del usuario

    @Query("SELECT l FROM LeagueEntity l JOIN l.bots b WHERE b.id = :botId")
    List<LeagueEntity> findLeaguesByBotId(Integer botId);

    boolean existsLeagueEntitiesByIdAndBots_Id(Integer id, Integer botId);
}
