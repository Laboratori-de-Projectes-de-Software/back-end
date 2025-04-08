package uib.lab.api.infraestructure.adapter;

import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.BotMapper;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.User;
import uib.lab.api.infraestructure.jpaRepositories.BotJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPort {

    private final BotJpaRepository botJpaRepository;
    private final BotMapper botMapper;
    private final UserMapper userMapper;

    public BotJpaAdapter(final BotJpaRepository botJpaRepository, final BotMapper botMapper, final UserMapper userMapper){
        this.botJpaRepository = botJpaRepository;
        this.botMapper = botMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<BotDomain> findById(int id) {
        return botJpaRepository.findBotById(id).map(botMapper::toDomain);
    }

    public List<BotDomain> findAllByUser(UserDomain userDomain) {
        User user = userMapper.toEntity(userDomain);

        return botJpaRepository.findAllByUser(user)
                .stream()
                .map(botMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public BotDomain save(BotDomain botDomain) {
        Bot botEntity = botMapper.toEntity(botDomain);
        Bot savedBot = botJpaRepository.save(botEntity);
        System.out.println("Bot guardado con ID: " + savedBot.getId());
        return botMapper.toDomain(savedBot);
    }
}
