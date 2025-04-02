package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.jpa.classification.ClassificationEntity;
import com.alia.back_end_service.jpa.game.GameEntity;
import com.alia.back_end_service.jpa.league.LeagueEntity;
import com.alia.back_end_service.jpa.league.LeagueMapper;
import com.alia.back_end_service.jpa.message.MessageEntity;
import com.alia.back_end_service.jpa.message.MessageMapper;
import com.alia.back_end_service.jpa.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotMapperImpl implements BotMapper{

    @Override
    public Bot toDomain(BotEntity entity) {
        List<Integer> leagueIds = (entity.getLeagues() != null)
                ? entity.getLeagues().stream()
                .map(LeagueEntity::getId)
                .collect(Collectors.toList())
                : List.of();

        List<Integer> messageIds = (entity.getMessages() != null)
                ? entity.getMessages().stream()
                .map(MessageEntity::getId)
                .collect(Collectors.toList())
                : List.of();

        List<Integer> localGameIds = (entity.getLocalGames() != null)
                ? entity.getLocalGames().stream()
                .map(GameEntity::getId)
                .collect(Collectors.toList())
                : List.of();
        List<Integer> visitorGameIds = (entity.getVisitorGames() != null)
                ? entity.getVisitorGames().stream()
                .map(GameEntity::getId)
                .collect(Collectors.toList())
                : List.of();
        List<Integer> classificationIds = (entity.getClassifications() != null)
                ? entity.getClassifications().stream().map(ClassificationEntity::getId).collect(Collectors.toList())
                : List.of();

        return new Bot(
                entity.getName(),
                entity.getDescription(),
                URI.create(entity.getEndpoint()),
                entity.getToken(),
                entity.getUser().getUsername(),
                leagueIds,
                messageIds,
                localGameIds,
                visitorGameIds,
                classificationIds
        );
    }

    @Override
    public BotEntity toEntity(Bot bot) {
        BotEntity entity = new BotEntity();
        entity.setName(bot.getName());
        entity.setDescription(bot.getDescription());
        entity.setEndpoint(bot.getEndpoint().toString());
        entity.setToken(bot.getToken());
        // La asignación de UserEntity y relaciones (leagues, messages, games, classifications) se realiza en el adaptador.
        return entity;
    }
}
