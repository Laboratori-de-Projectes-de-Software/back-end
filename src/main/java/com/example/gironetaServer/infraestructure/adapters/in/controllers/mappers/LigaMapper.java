package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LigaDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.BotJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LigaMapper {

    private final UserRepository userRepository;
    private final BotJpaRepository botJpaRepository;

    public LigaMapper(UserRepository userRepository, BotJpaRepository botJpaRepository) {
        this.userRepository = userRepository;
        this.botJpaRepository = botJpaRepository;
    }

    public League toDomain(LigaEntity ligaEntity) {
        League league = new League();
        league.setId(ligaEntity.getId());
        league.setName(ligaEntity.getName());
        league.setUrlImagen(ligaEntity.getUrlImagen());
        league.setRounds(ligaEntity.getRounds());
        league.setMatchTime(ligaEntity.getMatchTime());
        league.setUserId(ligaEntity.getUsuario().getId());

        List<Long> botIds = new ArrayList<>();
        if (ligaEntity.getBots() != null) {
            botIds = ligaEntity.getBots().stream()
                    .map(BotEntity::getId)
                    .collect(Collectors.toList());
        }
        league.setBots(botIds);

        return league;
    }

    public LigaEntity toEntity(League league) {
        LigaEntity ligaEntity = new LigaEntity();
        ligaEntity.setId(league.getId());
        ligaEntity.setName(league.getName());
        ligaEntity.setUrlImagen(league.getUrlImagen());
        ligaEntity.setRounds(league.getRounds());
        ligaEntity.setMatchTime(league.getMatchTime());
        UserEntity userEntity = UserMapper.toEntity(userRepository.getUserById(league.getUserId()));
        ligaEntity.setUsuario(userEntity);

        // Inicializamos un conjunto vacío o null - los bots se asociarán después de
        // guardar la liga
        if (league.getBots() == null || league.getBots().isEmpty()) {
            ligaEntity.setBots(new HashSet<>());
        } else {
            ligaEntity.setBots(null); // Se establecerá después de guardar la liga
        }

        return ligaEntity;
    }

    public LigaDto toLeagueDto(League league) {
        LigaDto ligaDto = new LigaDto();
        ligaDto.setId(league.getId());
        ligaDto.setName(league.getName());
        ligaDto.setUrlImagen(league.getUrlImagen());
        ligaDto.setRounds(league.getRounds());
        ligaDto.setMatchTime(league.getMatchTime());
        ligaDto.setBots(league.getBots());

        // Obtener el nombre del usuario (owner)
        if (league.getUserId() != null) {
            com.example.gironetaServer.domain.User user = userRepository.getUserById(league.getUserId());
            if (user != null) {
                ligaDto.setUser(user.getUsername());
            }
        }

        return ligaDto;
    }

    public static League toAppObject(LigaDto ligaDto) {
        League league = new League();
        league.setName(ligaDto.getName());
        league.setUrlImagen(ligaDto.getUrlImagen());
        league.setRounds(ligaDto.getRounds());
        league.setMatchTime(ligaDto.getMatchTime());
        league.setBots(ligaDto.getBots());
        return league;
    }
}
