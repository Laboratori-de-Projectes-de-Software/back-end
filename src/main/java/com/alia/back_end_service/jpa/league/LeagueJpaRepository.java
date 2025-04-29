package com.alia.back_end_service.jpa.league;


import com.alia.back_end_service.jpa.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeagueJpaRepository extends JpaRepository<LeagueEntity, Integer> {
    //@Query("SELECT l FROM LeagueEntity l JOIN l.bots b WHERE b.user.username = :username")
    List<LeagueEntity> findLeaguesByBots_User_Username(String username);

    @Query("SELECT l FROM LeagueEntity l JOIN l.bots b WHERE b.id = :botId")
    List<LeagueEntity> findLeaguesByBotId(Integer botId);

    boolean existsLeagueEntitiesByIdAndBots_Id(Integer id, Integer botId);

    @Query("""
    SELECT g FROM GameEntity g
    JOIN g.round r
    JOIN r.league l
    WHERE l.id = :leagueId
""")
    List<GameEntity> findAllGamesByLeagueId(@Param("leagueId") Integer leagueId);

    List<LeagueEntity> findLeaguesByOwner_Username(String ownerUsername);

}
