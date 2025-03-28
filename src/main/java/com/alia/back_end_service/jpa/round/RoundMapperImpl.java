package com.alia.back_end_service.jpa.round;

import com.alia.back_end_service.domain.round.Round;
import com.alia.back_end_service.jpa.classification.ClassificationMapper;
import com.alia.back_end_service.jpa.game.GameEntity;
import com.alia.back_end_service.jpa.game.GameMapper;
import com.alia.back_end_service.jpa.league.LeagueMapper;
import org.springframework.context.annotation.Lazy;


public class RoundMapperImpl implements RoundMapper{

    private final LeagueMapper leagueMapper;
    private final GameMapper gameMapper;
    private final ClassificationMapper classificationMapper;


    public RoundMapperImpl(@Lazy LeagueMapper leagueMapper,@Lazy GameMapper gameMapper,@Lazy ClassificationMapper classificationMapper) {
        this.leagueMapper = leagueMapper;
        this.gameMapper = gameMapper;
        this.classificationMapper = classificationMapper;
    }

    @Override
    public Round toDomain(RoundEntity entity) {
        return new Round(
                entity.getId(),
                entity.getNumber_round(),
                entity.getInit_time(),
                entity.getEnd_time(),
                entity.getState()
        );
    }

    @Override
    public RoundEntity toEntity(Round domain) {
        return new RoundEntity(
                domain.getId(),
                domain.getNumber_round(),
                domain.getInit_time(),
                domain.getEnd_time(),
                domain.getState()
        );
    }

}
