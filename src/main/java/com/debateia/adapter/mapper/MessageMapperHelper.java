package com.debateia.adapter.mapper;

import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.bot.BotJpaRepository;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.adapter.out.match.MatchJpaRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageMapperHelper {

    @Autowired
    private BotJpaRepository botRepository;

    @Autowired
    private MatchJpaRepository matchRepository;

    @Named("mapBotIdToEntity")
    public BotEntity mapBotIdToEntity(Integer botId) {
        if (botId == null) return null;

        Optional<BotEntity> existingBot = botRepository.findById(botId);

        if (existingBot.isPresent()) {
            return existingBot.get();
        }

        BotEntity bot = new BotEntity();
        bot.setId(botId);
        return bot;
    }

    @Named("mapMatchIdToEntity")
    public MatchEntity mapMatchIdToEntity(Integer matchId) {
        if (matchId == null) return null;

        Optional<MatchEntity> existingMatch = matchRepository.findById(matchId);

        if (existingMatch.isPresent()) {
            return existingMatch.get();
        }

        MatchEntity match = new MatchEntity();
        match.setId(matchId);
        return match;
    }
}