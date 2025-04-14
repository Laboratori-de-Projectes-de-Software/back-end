package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LeagueDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LeagueResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.BotJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigaMapper {

    private final UserRepository userRepository;
    private final BotJpaRepository botJpaRepository;

    public LigaMapper(UserRepository userRepository, BotJpaRepository botJpaRepository) {
        this.userRepository = userRepository;
        this.botJpaRepository = botJpaRepository;
    }

    public League toDomain(LeagueEntity ligaEntity) {
        League league = new League();
        league.setId(ligaEntity.getId());
        league.setName(ligaEntity.getName());
        league.setUrlImagen(ligaEntity.getUrlImagen());
        league.setRounds(ligaEntity.getRounds());
        league.setMatchTime(ligaEntity.getMatchTime());
        league.setUserId(ligaEntity.getUsuario().getId());
        league.setState(toDomainState(ligaEntity.getState()));

        List<Long> botIds = new ArrayList<>();
        if (ligaEntity.getBots() != null) {
            botIds = ligaEntity.getBots().stream()
                    .map(BotEntity::getId)
                    .collect(Collectors.toList());
        }
        league.setBots(botIds);

        return league;
    }

    public LeagueEntity toEntity(League league) {
        LeagueEntity ligaEntity = new LeagueEntity();
        ligaEntity.setId(league.getId());
        ligaEntity.setName(league.getName());
        ligaEntity.setUrlImagen(league.getUrlImagen());
        ligaEntity.setRounds(league.getRounds());
        ligaEntity.setMatchTime(league.getMatchTime());
        ligaEntity.setState(toEntityState(league.getState()));
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

    public LeagueDto toLeagueDto(League league) {
        LeagueDto ligaDto = new LeagueDto();
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

    public LeagueResponseDto toLeagueResponseDto(League league) {
        LeagueResponseDto leagueResponseDto = new LeagueResponseDto();
        leagueResponseDto.setLeagueId(league.getId().intValue());
        leagueResponseDto.setName(league.getName());
        leagueResponseDto.setUrlImagen(league.getUrlImagen());
        leagueResponseDto.setRounds(league.getRounds());
        leagueResponseDto.setMatchTime(league.getMatchTime());
        leagueResponseDto.setBots(league.getBots().stream()
                .map(Long::intValue)
                .collect(java.util.stream.Collectors.toList()));
        leagueResponseDto.setState(toEntityState(league.getState()));
        leagueResponseDto.setUser(league.getUserId().intValue());

        return leagueResponseDto;
    }

    public static League toAppObject(LeagueDto ligaDto) {
        League league = new League();
        league.setName(ligaDto.getName());
        league.setUrlImagen(ligaDto.getUrlImagen());
        league.setRounds(ligaDto.getRounds());
        league.setMatchTime(ligaDto.getMatchTime());
        league.setBots(ligaDto.getBots());
        return league;
    }

    // Función para convertir el estado del dominio al estado de la infraestructura
    public static LeagueEntity.State toEntityState(League.LeagueState domainState) {
        switch (domainState) {
            case Creada:
                return LeagueEntity.State.Created;
            case Empezada:
                return LeagueEntity.State.Started;
            case Terminada:
                return LeagueEntity.State.Finished;
            default:
                throw new IllegalArgumentException("Estado de liga no reconocido: " + domainState);
        }
    }

    // Función para convertir el estado de la infraestructura al estado del dominio
    public static League.LeagueState toDomainState(LeagueEntity.State entityState) {
        switch (entityState) {
            case Created:
                return League.LeagueState.Creada;
            case Started:
                return League.LeagueState.Empezada;
            case Finished:
                return League.LeagueState.Terminada;
            default:
                throw new IllegalArgumentException("Estado de liga no reconocido: " + entityState);
        }
    }

}
