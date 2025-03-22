package com.alia.back_end_service.jpa.game;

import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.jpa.bot.BotMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GameMapperImpl implements GameMapper {

    private final BotMapper botMapper;

    public GameMapperImpl(@Lazy BotMapper botMapper) {this.botMapper = botMapper;}

    @Override
    public Game toDomain(GameEntity entity) {
        return new Game(
                entity.getId(),
                entity.getTimestamp(),
                entity.getResult_local(),
                entity.getResult_visit(),
                botMapper.toDomain(entity.getBot_local_id()),
                botMapper.toDomain(entity.getBot_visit_id()),
                entity.getState()

        );
    }

    @Override
    public GameEntity toEntity(Game domain) {
        return new GameEntity(
                domain.getId(),
                domain.getTimestamp(),
                domain.getResult_local(),
                domain.getResult_visit(),
                botMapper.toEntity(domain.getBot_local_id()),
                botMapper.toEntity(domain.getBot_visit_id()),
                domain.getState()
        );
    }
}