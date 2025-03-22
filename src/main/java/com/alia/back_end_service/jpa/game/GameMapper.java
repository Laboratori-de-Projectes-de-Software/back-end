package com.alia.back_end_service.jpa.game;


import com.alia.back_end_service.domain.game.Game;

public interface GameMapper {
    Game toDomain(GameEntity entity);
    GameEntity toEntity(Game domain);
}
