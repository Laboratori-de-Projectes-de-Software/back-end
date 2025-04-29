package uib.lab.api.application.mapper.implementations;
import lombok.RequiredArgsConstructor;
import uib.lab.api.application.dto.chat.ChatDTO;
import uib.lab.api.application.mapper.interfaces.BotMapper;
import uib.lab.api.application.mapper.interfaces.ChatMapper;
import uib.lab.api.application.mapper.interfaces.MatchMapper;
import org.springframework.stereotype.Component;

import uib.lab.api.application.port.BotPort;
import uib.lab.api.application.port.MatchPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.ChatDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.infraestructure.jpaEntity.Chat;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ChatMapperImpl implements ChatMapper{
    private final MatchPort matchPort;
    private final MatchMapper matchMapper;
    private final BotPort botPort;
    private final BotMapper botMapper;

    @Override
    public ChatDomain toDomain(Chat entity){
        if(entity == null){
            return null;
        }

        return new ChatDomain(entity.getId(), entity.getContent(), entity.getDate().toString(), entity.getMatch().getId(), entity.getBot().getId());
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
            dto.getMatchId(),
            dto.getBotId()
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

        BotDomain bot = botPort.findById(chat.getBotId())
        .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + chat.getBotId()));
        entity.setBot(botMapper.toEntity(bot));

        return entity;
    }

}
