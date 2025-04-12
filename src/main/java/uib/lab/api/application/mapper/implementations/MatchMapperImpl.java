package uib.lab.api.application.mapper.implementations;

import uib.lab.api.application.mapper.interfaces.MatchMapper;
import uib.lab.api.application.mapper.interfaces.RoundMapper;
import lombok.RequiredArgsConstructor;
import uib.lab.api.application.mapper.interfaces.BotMapper;
import org.springframework.stereotype.Component;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.application.port.RoundPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.domain.RoundDomain;
import uib.lab.api.infraestructure.jpaEntity.Chat;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaRepositories.ChatJpaRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchMapperImpl implements MatchMapper{
    private final BotPort botPort;
    private final BotMapper botMapper;

    private final RoundPort roundPort;
    private final RoundMapper roundMapper;
    private final ChatJpaRepository chatJpaRepository;

    
    @Override
    public MatchDomain toDomain(Match entity){
        if(entity == null){
            return null;
        }

        int[] chatsId = entity.getChat() != null
        ? entity.getChat().stream().mapToInt(Chat::getId).toArray()
                : new int[0];

        return new MatchDomain(
            entity.getId(),
            entity.getState(),
            entity.getBot1().getId(),
            entity.getBot2().getId(),
            entity.getRound().getId(),
            chatsId
        );
    }

    @Override
    public Match toEntity(MatchDomain domain){
        Match entity = new Match();
        entity.setId(domain.getId());
        entity.setState(domain.getState());
        BotDomain bot1 = botPort.findById(domain.getBotId1())
                .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + domain.getBotId1()));
        entity.setBot1(botMapper.toEntity(bot1, false));

        BotDomain bot2 = botPort.findById(domain.getBotId2())
                .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + domain.getBotId2()));
        entity.setBot2(botMapper.toEntity(bot2, false));

        RoundDomain round = roundPort.findById(domain.getRoundId())
        .orElseThrow(() -> new IllegalArgumentException("Round not found with ID: " + domain.getRoundId()));
        entity.setRound(roundMapper.toEntity(round));

        if (domain.getChatsId() != null) {
            Set<Chat> chats = Arrays.stream(domain.getChatsId())
                    .mapToObj(id -> chatJpaRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Chat not found: " + id)))
                    .collect(Collectors.toSet());
            entity.setChat(chats);
        } else {
            entity.setChat(new HashSet<>());
        }

        return entity;

    }



}
