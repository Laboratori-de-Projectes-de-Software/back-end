package uib.lab.api.application.mapper.implementations;
import lombok.RequiredArgsConstructor;
import uib.lab.api.application.dto.league.LeagueDTO;
import uib.lab.api.application.mapper.interfaces.LeagueMapper;
import org.springframework.stereotype.Component;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaRepositories.BotJpaRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LeagueMapperImpl implements LeagueMapper {

    private final UserPort userPort;
    private final UserMapper userMapper;
    private final BotJpaRepository botJpaRepository;

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
    public LeagueDomain toDomain(LeagueDTO dto) {
        if (dto == null) {
            return null;
        }

        return new LeagueDomain(
                0,
                dto.getName(),
                dto.getUrlImagen(),
                dto.getMatchTime(),
                dto.getRounds(),
                League.LeagueState.PENDING,
                dto.getUserId(),
                dto.getBots() != null ? dto.getBots() : new int[0]
        );
    }

    @Override
    public League toEntity(LeagueDomain domain) {
        League entity = new League();
        entity.setName(domain.getName());
        entity.setPlayTime(domain.getPlayTime());
        entity.setNumRounds(domain.getNumRounds());
        entity.setState(domain.getState());
        entity.setUrlImagen(domain.getUrlImagen());
        UserDomain user = userPort.findById(domain.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + domain.getUserId()));
        entity.setUser(userMapper.toEntity(user, false));

        if (domain.getBotIds() != null) {
            Set<Bot> bots = Arrays.stream(domain.getBotIds())
                    .mapToObj(id -> botJpaRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Bot not found: " + id)))
                    .collect(Collectors.toSet());
            for (Bot bot : bots) {
                bot.getLeagues().add(entity);
            }
            entity.setBots(bots);
        } else {
            entity.setBots(new HashSet<>());
        }

        return entity;
    }
}

