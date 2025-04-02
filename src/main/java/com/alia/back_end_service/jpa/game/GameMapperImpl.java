package com.alia.back_end_service.jpa.game;

import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.jpa.bot.BotMapper;
import com.alia.back_end_service.jpa.message.MessageEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapperImpl implements GameMapper {

    private final BotMapper botMapper;

    public GameMapperImpl(@Lazy BotMapper botMapper) {this.botMapper = botMapper;}

    @Override
    public Game toDomain(GameEntity entity) {
        if (entity == null) return null;

        String botLocalId = (entity.getLocalBot() != null) ? entity.getLocalBot().getName() : null;
        String botVisitId = (entity.getVisitorBot() != null) ? entity.getVisitorBot().getName() : null;

        Integer roundId = (entity.getRound() != null) ? entity.getRound().getId() : null;

        List<Integer> messageIds = (entity.getMessages() != null)
                ? entity.getMessages().stream()
                .map(MessageEntity::getId)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return new Game(
                entity.getId(),
                entity.getTimestamp(),
                entity.getResult_local(),
                entity.getResult_visit(),
                botLocalId,
                botVisitId,
                roundId,
                messageIds,
                entity.getState()
        );
    }

    @Override
    public GameEntity toEntity(Game domain) {
        if (domain == null) return null;

        GameEntity entity = new GameEntity();
        entity.setId(domain.getId());
        entity.setTimestamp(domain.getTimestamp());
        entity.setResult_local(domain.getResult_local());
        entity.setResult_visit(domain.getResult_visit());
        // Las relaciones (BotEntity, RoundEntity y MessageEntity) se asignan desde fuera en el adaptador.
        entity.setState(domain.getState());
        return entity;
    }
}