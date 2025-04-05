package com.alia.back_end_service.jpa.game;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.game.ports.GamePortDB;
import com.alia.back_end_service.domain.round.ports.RoundPortDB;
import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.bot.BotJpaRepository;
import com.alia.back_end_service.jpa.round.RoundEntity;
import com.alia.back_end_service.jpa.round.RoundJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class GameJpaAdapter implements GamePortDB {

    private final GameJpaRepository repository;
    private final RoundJpaRepository roundJpaRepository;
    private final BotJpaRepository botJpaRepository;
    private final GameMapper gameMapper;

    @Override
    public Optional<Game> findById(Integer game_id) {
        Optional<GameEntity> gameEntityOptional = repository.findById(game_id);
        return gameEntityOptional.map(gameMapper::toDomain);
    }

    @Override
    public Game saveGame(Game game) {
        GameEntity gameEntity = gameMapper.toEntity(game);
        RoundEntity roundEntity = roundJpaRepository.findById(game.getRoundId()).orElse(null);
        BotEntity local = botJpaRepository.findById(game.getBot_local_id()).orElse(null);
        BotEntity visitante = botJpaRepository.findById(game.getBot_visit_id()).orElse(null);
        gameEntity.setRound(roundEntity);
        gameEntity.setLocalBot(local);
        gameEntity.setVisitorBot(visitante);
        GameEntity gameSaved = repository.save(gameEntity);
        return gameMapper.toDomain(gameSaved);
    }

    @Override
    public void deleteGame(Integer game_id) {
        repository.deleteById(game_id);
    }
}
