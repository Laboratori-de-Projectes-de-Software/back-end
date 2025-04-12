package uib.lab.api.application.mapper.implementations;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.BotMapper;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.Chat;
import uib.lab.api.infraestructure.jpaEntity.Match;
import java.util.HashSet;
import java.util.Set;

@Component
public class BotMapperImpl implements BotMapper {

    private UserPort userPort;
    private UserMapper userMapper;
    private MatchPort matchPort;

    public BotMapperImpl(UserPort userPort, UserMapper userMapper) {
        this.userPort = userPort;
        this.userMapper = userMapper;
    }

    @Override
    public BotDomain toDomain(Bot entity){
        if (entity == null) {
            return null;
        }
        return new BotDomain(entity.getId(),
                entity.getIdeologia(),
                entity.getUrl(),
                entity.getDescription(),
                entity.getImagen(),
                entity.getNWins(),
                entity.getNLosses(),
                entity.getNDraws(),
                entity.getUser().getId(),
                extractMatchIds(entity.getMatchAsBot1()),
                extractMatchIds(entity.getMatchAsBot2()),
                extractChatsIds(entity.getChats()));
    }

    @Override
    public Bot toEntity(BotDomain bot) {
        return toEntity(bot, true);
    }

    public Bot toEntity(BotDomain bot, boolean includeUser){
        if (bot == null) {
            return null;
        }

        Bot entity = new Bot();
        entity.setId(bot.getId());
        entity.setIdeologia(bot.getIdeologia());
        entity.setDescription(bot.getDescription());
        entity.setImagen(bot.getUrlImagen());
        entity.setUrl(bot.getUrl());
        entity.setNWins(bot.getNWins());
        entity.setNLosses(bot.getNLosses());
        entity.setNDraws(bot.getNDraws());

        if (includeUser) {
            UserDomain user = userPort.findById(bot.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + bot.getUserId()));
            entity.setUser(userMapper.toEntity(user, false));
        }

        entity.setMatchAsBot1(new HashSet<>());
        entity.setMatchAsBot2(new HashSet<>());
        entity.setChats(new HashSet<>());

        return entity;
    }

    private int[] extractMatchIds(Set<Match> matches) {
        if (matches == null) return new int[0];
        return matches.stream()
                .mapToInt(Match::getId)
                .toArray();
    }

    private int[] extractChatsIds(Set<Chat> chats){
        if (chats == null) return new int[0];
        return chats.stream()
                .mapToInt(Chat::getId)
                .toArray();
    }
}
