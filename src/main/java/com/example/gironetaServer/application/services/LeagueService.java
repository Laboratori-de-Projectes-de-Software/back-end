package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.LeagueRepository;
import com.example.gironetaServer.application.usecases.users.CreateLeague;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.TimeoutException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.BotJpaRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.LigaJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();
        league.setUserId(userId);

        // Guardar la liga primero sin bots
        League savedLeague;
        try {
            savedLeague = leagueRepository.save(league);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("nombre")) {
                throw new ConflictException("Ya existe una liga con el mismo nombre");
            } else {
                throw e;
            }
        }

        // Si hay bots para asociar, hacerlo después de guardar la liga
        if (league.getBots() != null && !league.getBots().isEmpty()) {
            try {
                // Recuperar la entidad de liga guardada
                LigaEntity ligaEntity = ligaJpaRepository.findById(savedLeague.getId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("No se pudo encontrar la liga recién guardada"));

                // Crear un conjunto para almacenar los bots
                Set<BotEntity> botEntities = new HashSet<>();

                // Obtener cada bot y agregarlo al conjunto sólo si existe
                for (Long botId : league.getBots()) {
                    BotEntity botEntity = botJpaRepository.findById(botId)
                            .orElseThrow(() -> new ResourceNotFoundException("Bot no encontrado con ID: " + botId));

                    botEntities.add(botEntity);
                }

                // Establecer los bots en la entidad de liga
                ligaEntity.setBots(botEntities);

                // Guardar la liga actualizada con los bots
                try {
                    ligaJpaRepository.save(ligaEntity);
                } catch (DataIntegrityViolationException e) {
                    throw new ConflictException("Error al asociar bots a la liga: " + e.getMessage());
                }

                // Actualizar el objeto de dominio con la entidad fresca
                savedLeague = leagueRepository.findById(savedLeague.getId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("No se pudo encontrar la liga recién guardada"));
            } catch (Exception e) {
                if (e instanceof ResourceNotFoundException ||
                        e instanceof ForbiddenException ||
                        e instanceof ConflictException ||
                        e instanceof TimeoutException) {
                    throw e;
                }
                throw new ConflictException("Error al asociar bots a la liga: " + e.getMessage());
            }
        }

        return savedLeague;
    }

    public Optional<League> findById(Long id) {
        return leagueRepository.findById(id);
    }

    public List<League> findByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();
        return leagueRepository.findByUserId(userId);
    }

    public League getLeagueById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Liga no encontrada con id: " + id));

        // Verificar si el usuario tiene acceso a esta liga
        if (!league.getUserId().equals(userId)) {
            throw new ForbiddenException("No tienes permiso para ver esta liga");
        }

        return league;
    }

    @Transactional
    public void deleteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();

        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Liga no encontrada con id: " + id));

        if (!league.getUserId().equals(userId)) {
            throw new ForbiddenException("No tienes permiso para eliminar esta liga");
        }

        try {
            leagueRepository.deleteById(id);
        } catch (Exception e) {
            throw new ConflictException("No se pudo eliminar la liga, posiblemente tiene datos asociados");
        }
    }
}