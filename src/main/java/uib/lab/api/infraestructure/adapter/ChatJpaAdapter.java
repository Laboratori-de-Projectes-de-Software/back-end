package uib.lab.api.infraestructure.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import uib.lab.api.application.mapper.interfaces.ChatMapper;
import uib.lab.api.application.mapper.interfaces.MatchMapper;
import uib.lab.api.application.port.ChatPort;
import uib.lab.api.domain.ChatDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaRepositories.ChatJpaRepository;


@Component
public class ChatJpaAdapter implements ChatPort{
    private final ChatJpaRepository chatJpaRepository;
    private final ChatMapper chatMapper;
    private final MatchMapper matchMapper;

    public ChatJpaAdapter(final ChatJpaRepository chatJpaRepository, final ChatMapper chatMapper, final MatchMapper matchMapper){
        this.chatJpaRepository = chatJpaRepository;
        this.chatMapper = chatMapper;
        this.matchMapper = matchMapper;
    }

    @Override
    public List<ChatDomain> findAllByMatch(MatchDomain domain){
        Match match = matchMapper.toEntity(domain);

        return chatJpaRepository.findAllByMatch(match)
                .stream()
                .map(chatMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatDomain> findAllChats(){
        return chatJpaRepository.findAll()
        .stream()
        .map(chatMapper::toDomain)
        .collect(Collectors.toList());
    }
}
