package uib.lab.api.application.mapper.implementations;
import lombok.RequiredArgsConstructor;
import uib.lab.api.application.dto.chat.ChatDTO;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.mapper.interfaces.BotMapper;
import uib.lab.api.application.mapper.interfaces.ChatMapper;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import uib.lab.api.application.mapper.interfaces.MatchMapper;
import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.application.port.ChatPort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.ChatDomain;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Chat;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaRepositories.BotJpaRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatMapperImpl implements ChatMapper{
    private final MatchPort matchPort;
    private final MatchMapper matchMapper;

    @Override
    public ChatDomain toDomain(Chat entity){
        if(entity == null){
            return null;
        }

        return new ChatDomain(entity.getId(), entity.getContent(), entity.getDate().toString(), entity.getMatch().getId());
    }

    @Override
    public ChatDomain toDomain(ChatDTO dto){
        if(dto == null){
            return null;
        }

        return new ChatDomain(
            0,
            dto.getText(),
            dto.getTime(),
            dto.getMatchId()
        );
    }


    @Override
    public Chat toEntity(ChatDomain chat){
        Chat entity = new Chat();
        entity.setId(chat.getId());
        entity.setContent(chat.getText());
        entity.setDate(LocalDateTime.parse(chat.getTime()));

        MatchDomain match = matchPort.findById(chat.getMatchId())
        .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + chat.getMatchId()));
        entity.setMatch(matchMapper.toEntity(match));

        return entity;
    }

}
