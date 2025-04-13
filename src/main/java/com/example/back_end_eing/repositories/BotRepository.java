package com.example.back_end_eing.repositories;

import com.example.back_end_eing.models.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BotRepository extends JpaRepository<Bot, Long> {

    Optional<Bot> findByApiKey(String apiKey);

    @Query(value = "SELECT bot.* FROM bot " +
                        "JOIN clasificacion ON bot.id = clasificacion.bot_id " +
                        "JOIN liga ON clasificacion.liga_id = liga.id " +
                    "WHERE liga.id = :leagueId", nativeQuery = true)
    List<Bot> findByLeague(@Param("leagueId") Long leagueId);

    Optional <List<Bot>>findByUsuarioId(Long userId);

    Optional <Bot>findByNombreBot(String nombre);
}
