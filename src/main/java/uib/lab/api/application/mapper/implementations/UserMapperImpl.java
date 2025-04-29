package uib.lab.api.application.mapper.implementations;

import uib.lab.api.application.mapper.interfaces.BotMapper;
import uib.lab.api.application.mapper.interfaces.UserMapper;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    private BotPort botPort;
    private BotMapper botMapper;

    @Override
    public UserDomain toDomain(User entity) {
        if (entity == null) {
            return null;
        }

        UserDomain domain = new UserDomain(
                entity.getId(),
                entity.getMail(),
                entity.getUsername(),
                entity.getPassword(),
                entity.isEnabled(),
                entity.getRoles()
        );

        if (entity.getBots() != null) {
            int[] botsId = entity.getBots().stream()
                    .mapToInt(bot -> (int) bot.getId())
                    .toArray();
            domain.setBotsId(botsId);
        }

        if (entity.getLeagues() != null) {
            int[] leagueIds = entity.getLeagues().stream()
                    .mapToInt(League::getId)
                    .toArray();
            domain.setLeaguesId(leagueIds);
        }

        return domain;
    }

    @Override
    public User toEntity(UserDomain user) {
        return toEntity(user, false);
    }

    public User toEntity(UserDomain user, boolean includeBots){
        if (user == null) {
            return null;
        }

        User entity = new User();
        entity.setId(user.getId());
        entity.setMail(user.getMail());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEnabled(true);
        entity.setRoles(user.getRoles());

        if (includeBots && user.getBotsId() != null) {
            Set<Bot> bots = Arrays.stream(user.getBotsId())
                    .mapToObj(id -> botPort.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + id)))
                    .map(bot -> botMapper.toEntity(bot, false))
                    .collect(Collectors.toSet());
            entity.setBots(bots);
        }

        return entity;
    }

}
