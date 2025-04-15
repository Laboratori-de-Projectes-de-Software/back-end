package com.debateia.application.mapper;

import com.debateia.adapter.out.persistence.UserResponseDTO;
import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getUserId());
        entity.setUsername(user.getUsername());
        entity.setMail(user.getMail());
        entity.setPassword(user.getPassword());
        if (user.getLeagueId() != null) {
            LeagueEntity dummyL = new LeagueEntity();
            dummyL.setId(user.getLeagueId());
            entity.setLeague(dummyL);
        }
        if (user.getBotsId() != null) {
            // para cada ID crea un BotEntity asignandole ese ID
            List<BotEntity> dummyB = user.getBotsId().stream()
                    .map(id -> {
                        BotEntity botEntity = new BotEntity();
                        botEntity.setId(id); // Asignar el id del Bot
                        return botEntity;
                    })
                    .toList();
            entity.setBots(dummyB);
        }
        return entity;
    }

    public static User entityToDomain(UserEntity entity) {
        User dom = new User();
        dom.setUserId(entity.getId());
        dom.setPassword(entity.getPassword());
        dom.setMail(entity.getMail());
        dom.setUsername(entity.getUsername());
        if (entity.getLeague() != null) {
            dom.setLeagueId(entity.getLeague().getId());
        } else {
            dom.setLeagueId(null);
        }
        /* // esto no hace falta para login/register/auth y peta porque los bots no estan cargados y la consulta ya acabo
        // solucion: anadir bots en el service ejecutando el repo si se necesitan
        if (entity.getBots() != null) { // saca lista de IDs a partir de lista de BotEntity
            List<Integer> ids = entity.getBots().stream()
                    .map(BotEntity::getId)
                    .toList();
            dom.setBotsId(ids);
        }
        */
        return dom;
    }

    public static UserResponseDTO toResponseDTO(User dom) {
        return new UserResponseDTO(dom.getToken(), dom.getExpiresIn(), dom.getUsername());
    }
}
