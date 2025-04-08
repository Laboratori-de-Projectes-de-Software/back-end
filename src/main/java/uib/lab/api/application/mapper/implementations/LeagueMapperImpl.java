package uib.lab.api.application.mapper.implementations;
import uib.lab.api.application.mapper.interfaces.BotMapper;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LeagueMapperImpl implements LeagueMapper {

    private final BotPort botPort;
    private final UserPort userPort;
    private final UserMapper userMapper;
    private final BotMapper botMapper;

    public LeagueMapperImpl(BotPort botPort, UserPort userPort, UserMapper userMapper, BotMapper botMapper) {
        this.botPort = botPort;
        this.userPort = userPort;
        this.userMapper = userMapper;
        this.botMapper = botMapper;
    }

    @Override
    public LeagueDomain toDomain(League entity) {
        if (entity == null) {
            return null;
        }

        int[] botIds = entity.getBots() != null
                ? entity.getBots().stream().mapToInt(Bot::getId).toArray()
                : new int[0];

        return new LeagueDomain(
                entity.getId(),
                entity.getName(),
                entity.getUrlImagen(),
                entity.getPlayTime(),
                entity.getNumRounds(),
                entity.getState(),
                entity.getUser().getId(),
                botIds
        );
    }

    @Override
    public League toEntity(LeagueDomain domain) {
        return toEntity(domain, true);
    }

    public League toEntity(LeagueDomain domain, boolean includeUser) {
        if (domain == null) {
            return null;
        }

        League entity = new League();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setUrlImagen(domain.getUrlImagen());
        entity.setPlayTime(domain.getPlayTime());
        entity.setNumRounds(domain.getNumRounds());
        entity.setState(domain.getState());

        // Asignar usuario creador
        if (includeUser) {
            UserDomain user = userPort.findById(domain.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + domain.getUserId()));
            entity.setUser(userMapper.toEntity(user, false));
        }

        // Asignar bots a la liga
        if (domain.getBotIds() != null) {
            Set<Bot> bots = Arrays.stream(domain.getBotIds())
                    .mapToObj(id -> botPort.findById(id)
                            .map(botDomain -> botMapper.toEntity(botDomain, false))
                            .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + id)))
                    .collect(Collectors.toSet());
            entity.setBots(bots);
        } else {
            entity.setBots(new HashSet<>());
        }

        return entity;
    }
}

