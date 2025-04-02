package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.LeagueRepository;
import com.example.gironetaServer.application.usecases.users.CreateLeague;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.BotJpaRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.LigaJpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeagueService implements CreateLeague {

    private final LeagueRepository leagueRepository;
    private final BotJpaRepository botJpaRepository;
    private final LigaJpaRepository ligaJpaRepository;

    public LeagueService(LeagueRepository leagueRepository, BotJpaRepository botJpaRepository,
            LigaJpaRepository ligaJpaRepository) {
        this.leagueRepository = leagueRepository;
        this.botJpaRepository = botJpaRepository;
        this.ligaJpaRepository = ligaJpaRepository;
    }

    @Override
    @Transactional
    public League createLeague(League league) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        league.setUserId(usuario);

        // Guardar la liga primero sin bots
        League savedLeague = leagueRepository.save(league);

        // Si hay bots para asociar, hacerlo después de guardar la liga
        if (league.getBots() != null && !league.getBots().isEmpty()) {
            // Recuperar la entidad de liga guardada
            LigaEntity ligaEntity = ligaJpaRepository.findById(savedLeague.getId())
                    .orElseThrow(() -> new RuntimeException("No se pudo encontrar la liga recién guardada"));

            // Recuperar las entidades de bot por ID
            Set<BotEntity> botEntities = league.getBots().stream()
                    .map(botId -> botJpaRepository.findById(botId)
                            .orElseThrow(() -> new RuntimeException("Bot no encontrado con ID: " + botId)))
                    .collect(Collectors.toSet());

            // Establecer la relación
            ligaEntity.setBots(botEntities);

            // Guardar la liga actualizada
            ligaJpaRepository.save(ligaEntity);

            // Actualizar el objeto de dominio con los IDs de bot
            savedLeague.setBots(league.getBots());
        }

        return savedLeague;
    }
}