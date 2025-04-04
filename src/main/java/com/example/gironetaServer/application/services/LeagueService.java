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
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.UserJpaRepository;
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
    private final UserJpaRepository userJpaRepository;

    public LeagueService(LeagueRepository leagueRepository, BotJpaRepository botJpaRepository,
            LigaJpaRepository ligaJpaRepository, UserJpaRepository userJpaRepository) {
        this.leagueRepository = leagueRepository;
        this.botJpaRepository = botJpaRepository;
        this.ligaJpaRepository = ligaJpaRepository;
        this.userJpaRepository = userJpaRepository;
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

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public List<League> getAllLeagues(Long ownerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        if (ownerId != null) {
            // Verificar si el usuario existe
            if (!userJpaRepository.existsById(ownerId)) {
                throw new ResourceNotFoundException("Usuario no encontrado con id: " + ownerId);
            }
            return leagueRepository.findByUserId(ownerId);
        } else {
            return leagueRepository.findAll();
        }
    }

    @Transactional
    public League updateLeague(Long leagueId, League leagueDetails) {
        if (leagueId == null || leagueId <= 0) {
            throw new IllegalArgumentException("El ID de la liga no puede ser nulo o menor o igual a cero");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();

        // Verificar si la liga existe
        League existingLeague = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new ResourceNotFoundException("Liga no encontrada con id: " + leagueId));

        // Verificar si el usuario tiene permiso para editar esta liga
        if (!existingLeague.getUserId().equals(userId)) {
            throw new ForbiddenException("No tienes permiso para editar esta liga");
        }

        // Actualizar los campos de la liga existente
        existingLeague.setName(leagueDetails.getName());
        existingLeague.setUrlImagen(leagueDetails.getUrlImagen());
        existingLeague.setRounds(leagueDetails.getRounds());
        existingLeague.setMatchTime(leagueDetails.getMatchTime());

        // Guardar la liga actualizada sin los bots
        League updatedLeague;
        try {
            // Mantener el ID y el userID originales
            leagueDetails.setId(leagueId);
            leagueDetails.setUserId(userId);

            updatedLeague = leagueRepository.save(existingLeague);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("nombre")) {
                throw new ConflictException("Ya existe otra liga con ese nombre");
            } else {
                throw e;
            }
        }

        // Actualizar los bots asociados a la liga
        if (leagueDetails.getBots() != null) {
            try {
                // Recuperar la entidad de liga actualizada
                LigaEntity ligaEntity = ligaJpaRepository.findById(updatedLeague.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "No se pudo encontrar la liga recién actualizada"));

                // Crear un conjunto para almacenar los bots
                Set<BotEntity> botEntities = new HashSet<>();

                // Obtener cada bot y agregarlo al conjunto sólo si existe
                for (Long botId : leagueDetails.getBots()) {
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
                updatedLeague = leagueRepository.findById(updatedLeague.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "No se pudo encontrar la liga recién actualizada"));
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

        return updatedLeague;
    }

    @Transactional
    public void registerBotToLeague(Long leagueId, Long botId) {
        if (leagueId == null || leagueId <= 0) {
            throw new IllegalArgumentException("El ID de la liga no puede ser nulo o menor o igual a cero");
        }

        if (botId == null || botId <= 0) {
            throw new IllegalArgumentException("El ID del bot no puede ser nulo o menor o igual a cero");
        }

        // Verificar autenticación
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();

        // Verificar si la liga existe
        LigaEntity ligaEntity = ligaJpaRepository.findById(leagueId)
                .orElseThrow(() -> new ResourceNotFoundException("Liga no encontrada con id: " + leagueId));

        // Verificar si el usuario tiene permisos para modificar esta liga
        if (!ligaEntity.getUsuario().getId().equals(userId)) {
            throw new ForbiddenException("No tienes permiso para modificar esta liga");
        }

        // Verificar si el bot existe
        BotEntity botEntity = botJpaRepository.findById(botId)
                .orElseThrow(() -> new ResourceNotFoundException("Bot no encontrado con id: " + botId));

        // Verificar si el bot ya está registrado en la liga
        if (ligaEntity.getBots() != null && ligaEntity.getBots().stream().anyMatch(b -> b.getId().equals(botId))) {
            throw new ConflictException("El bot ya está registrado en esta liga");
        }

        // Registrar el bot en la liga
        Set<BotEntity> bots = ligaEntity.getBots();
        if (bots == null) {
            bots = new HashSet<>();
        }
        bots.add(botEntity);
        ligaEntity.setBots(bots);

        // Guardar la liga actualizada
        try {
            ligaJpaRepository.save(ligaEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Error al registrar el bot en la liga: " + e.getMessage());
        } catch (Exception e) {
            throw new ConflictException("Error inesperado al registrar el bot en la liga: " + e.getMessage());
        }
    }
}