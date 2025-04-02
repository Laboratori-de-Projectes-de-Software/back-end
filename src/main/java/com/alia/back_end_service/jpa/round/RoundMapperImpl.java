package com.alia.back_end_service.jpa.round;

import com.alia.back_end_service.domain.round.Round;
import com.alia.back_end_service.jpa.classification.ClassificationEntity;
import com.alia.back_end_service.jpa.classification.ClassificationMapper;
import com.alia.back_end_service.jpa.game.GameEntity;
import com.alia.back_end_service.jpa.game.GameMapper;
import com.alia.back_end_service.jpa.league.LeagueMapper;
import org.springframework.context.annotation.Lazy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class RoundMapperImpl implements RoundMapper{

    public Round toDomain(RoundEntity entity) {
        if (entity == null) return null;

        Round round = new Round();
        round.setId(entity.getId());
        round.setNumber_round(entity.getNumber_round());
        round.setInit_time(entity.getInit_time());
        round.setEnd_time(entity.getEnd_time());
        round.setState(entity.getState());

        // Extraer el ID de la liga
        round.setLeagueId(entity.getLeague() != null ? entity.getLeague().getId() : null);

        // Extraer IDs de los juegos
        if (entity.getGames() != null) {
            List<Integer> gameIds = entity.getGames().stream()
                    .map(GameEntity::getId)
                    .collect(Collectors.toList());
            round.setGameIds(gameIds);
        } else {
            round.setGameIds(Collections.emptyList());
        }

        // Extraer IDs de las clasificaciones
        if (entity.getClassifications() != null) {
            List<Integer> classificationIds = entity.getClassifications().stream()
                    .map(ClassificationEntity::getId)
                    .collect(Collectors.toList());
            round.setClassificationIds(classificationIds);
        } else {
            round.setClassificationIds(Collections.emptyList());
        }

        return round;
    }

    @Override
    public RoundEntity toEntity(Round domain) {
        if (domain == null) return null;

        RoundEntity entity = new RoundEntity();
        entity.setId(domain.getId());
        entity.setNumber_round(domain.getNumber_round());
        entity.setInit_time(domain.getInit_time());
        entity.setEnd_time(domain.getEnd_time());
        entity.setState(domain.getState());
        // La asociación con League, Games y Classifications se gestiona en otra capa (en el adaptador)
        return entity;
    }

}
