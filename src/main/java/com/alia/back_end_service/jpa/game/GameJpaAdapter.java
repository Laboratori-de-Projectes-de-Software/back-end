package com.alia.back_end_service.jpa.game;

import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.game.ports.GamePortDB;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameJpaAdapter implements GamePortDB {

    private final GameJpaRepository repository;

    private final GameMapper gameMapper;

    public GameJpaAdapter(GameJpaRepository repository, GameMapper gameMapper) {
        this.repository = repository;
        this.gameMapper = gameMapper;
    }

    @Override
    public Optional<Game> findById(Integer game_id) {
        Optional<GameEntity> gameEntityOptional = repository.findById(game_id);
        return gameEntityOptional.map(gameMapper::toDomain);
    }

    @Override
    public Game saveGame(Game game) {
        GameEntity gameSaved = repository.save(gameMapper.toEntity(game));
        return gameMapper.toDomain(gameSaved);
    }

    @Override
    public void deleteGame(Integer game_id) {
        repository.deleteById(game_id);
    }
}
