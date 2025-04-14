package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.EnfrentamientoRepository;
import com.example.gironetaServer.application.ports.LeagueRepository;
import com.example.gironetaServer.application.ports.ParticipacionRepository;
import com.example.gironetaServer.application.usecases.users.CreateLeague;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.TimeoutException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.MatchResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.*;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LeagueService implements CreateLeague {

    private final LeagueRepository leagueRepository;
    private final BotJpaRepository botJpaRepository;
    private final LigaJpaRepository ligaJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final JornadaJpaRepository jornadaJpaRepository;
    private final EnfrentamientoJpaRepository enfrentamientoJpaRepository;

    private final ParticipacionRepository participacionRepository;
    private final ParticipacionJpaRepository participacionJpaRepository; //Not should be here, but just in case

    private final EnfrentamientoRepository enfrentamientoRepository;


    public LeagueService(LeagueRepository leagueRepository, BotJpaRepository botJpaRepository,
                         LigaJpaRepository ligaJpaRepository, UserJpaRepository userJpaRepository, JornadaJpaRepository jornadaJpaRepository, EnfrentamientoJpaRepository enfrentamientoJpaRepository,
                         ParticipacionRepository participacionRepository, ParticipacionJpaRepository participacionJpaRepository,
                         EnfrentamientoRepository enfrentamientoRepository
                         ) {
        this.leagueRepository = leagueRepository;
        this.botJpaRepository = botJpaRepository;
        this.ligaJpaRepository = ligaJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.jornadaJpaRepository = jornadaJpaRepository;
        this.enfrentamientoJpaRepository = enfrentamientoJpaRepository;
        this.participacionRepository = participacionRepository;
        this.participacionJpaRepository = participacionJpaRepository;
        this.enfrentamientoRepository = enfrentamientoRepository;

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
                LeagueEntity ligaEntity = ligaJpaRepository.findById(savedLeague.getId())
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
                LeagueEntity ligaEntity = ligaJpaRepository.findById(updatedLeague.getId())
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
        LeagueEntity ligaEntity = ligaJpaRepository.findById(leagueId)
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

    @Transactional
    public void deleteLeague(Long leagueId) {
        if (leagueId == null || leagueId <= 0) {
            throw new IllegalArgumentException("El ID de la liga no puede ser nulo o menor o igual a cero");
        }

        // Verificar autenticación
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();

        // Verificar si la liga existe
        LeagueEntity ligaEntity = ligaJpaRepository.findById(leagueId)
                .orElseThrow(() -> new ResourceNotFoundException("Liga no encontrada con id: " + leagueId));

        // Verificar si el usuario tiene permisos para modificar esta liga
        if (!ligaEntity.getUsuario().getId().equals(userId)) {
            throw new ForbiddenException("No tienes permiso para modificar esta liga");
        }

        // Eliminar la liga
        try {
            ligaJpaRepository.delete(ligaEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Error al eliminar la liga: " + e.getMessage());
        } catch (Exception e) {
            throw new ConflictException("Error inesperado al eliminar la liga: " + e.getMessage());
        }
    }

    @Transactional
    public void startLeague(Long leagueId) {
        if (leagueId == null || leagueId <= 0) {
            throw new IllegalArgumentException("El ID de la liga no puede ser nulo o menor o igual a cero");
        }

        // Verificar autenticación
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();

        // Verificar si la liga existe
        LeagueEntity ligaEntity = ligaJpaRepository.findById(leagueId)
                .orElseThrow(() -> new ResourceNotFoundException("Liga no encontrada con id: " + leagueId));

        // Verificar si el usuario tiene permisos para modificar esta liga
        if (!ligaEntity.getUsuario().getId().equals(userId)) {
            throw new ForbiddenException("No tienes permiso para modificar esta liga");
        }

        if (ligaEntity.getState() == LeagueEntity.State.Started) {
            throw new ConflictException("La liga ya está empezada y no se puede volver a iniciar");
        }

        // Cambiar el estado de la liga a "Started"
        try {
            ligaEntity.setState(LeagueEntity.State.Started);
            ligaJpaRepository.save(ligaEntity);

            int rounds = ligaEntity.getRounds();

            // Obtener la lista de bots de la liga
            List<BotEntity> bots = new ArrayList<>(ligaEntity.getBots());
            int numBots = bots.size();
            int totalJornadas = (numBots - 1) * rounds;

            // Guardar todos los enfrentamientos previos
            Set<String> enfrentamientosPrevios = new HashSet<>();

            // Crear las jornadas
            for (int i = 1; i <= totalJornadas; i++) {
                JornadaEntity jornadaEntity = new JornadaEntity();
                jornadaEntity.setLiga(ligaEntity);
                jornadaEntity.setNumJornada(i);

                // Crear enfrentamientos únicos para la jornada
                HashSet<EnfrentamientoEntity> enfrentamientos = new HashSet<>();

                // Si el número de bots es impar, agregar un bot ficticio
                if (numBots % 2 != 0) {
                    BotEntity byeBot = new BotEntity();
                    byeBot.setId(-1L); // ID ficticio para el bot "bye"
                    bots.add(byeBot);
                    numBots++;
                }

                for (int j = 0; j < numBots / 2; j++) {
                    BotEntity bot1 = bots.get(j);
                    BotEntity bot2 = bots.get(numBots - 1 - j);

                    // Saltar enfrentamientos contra el bot ficticio
                    if (bot1.getId() == -1L || bot2.getId() == -1L) {
                        continue;
                    }

                    String enfrentamientoKey = bot1.getId() + "-" + bot2.getId();
                    String enfrentamientoKeyReverso = bot2.getId() + "-" + bot1.getId();

                    // Verificar que el enfrentamiento no se haya repetido más de 'rounds' veces
                    long countEnfrentamientos = enfrentamientosPrevios.stream()
                            .filter(key -> key.equals(enfrentamientoKey) || key.equals(enfrentamientoKeyReverso))
                            .count();

                    if (countEnfrentamientos < rounds) {
                        EnfrentamientoEntity enfrentamientoEntity = new EnfrentamientoEntity();
                        enfrentamientoEntity.setJornada(jornadaEntity);
                        enfrentamientoEntity.setEstado(EnfrentamientoEntity.State.PENDANT);

                        // Decidir bot local y visitante
                        BotEntity botLocal;
                        BotEntity botVisitante;

                        if (jornadaEntity.getNumJornada() % 2 == 0) { // Jornada par
                            botLocal = bot1;
                            botVisitante = bot2;
                        } else { // Jornada impar
                            botLocal = bot2;
                            botVisitante = bot1;
                        }

                        //Añadir bots locales y visitantes junto a sus puntuaciones
                        enfrentamientoEntity.setBotLocal(botLocal);
                        enfrentamientoEntity.setPuntuacionLocal(0);
                        enfrentamientoEntity.setBotVisitante(botVisitante);
                        enfrentamientoEntity.setPuntuacionVisitante(0);

                        //Añadir la ronda que es
                        enfrentamientoEntity.setRonda(((jornadaEntity.getNumJornada() - 1) / (numBots - 1)) + 1);

                        enfrentamientos.add(enfrentamientoEntity);
                        enfrentamientosPrevios.add(enfrentamientoKey);
                    }
                }

                jornadaEntity.setEnfrentamientos(enfrentamientos);
                jornadaJpaRepository.save(jornadaEntity);

                // Guardar cada enfrentamiento y sus resultados
                for (EnfrentamientoEntity enfrentamiento : enfrentamientos) {
                    enfrentamientoRepository.save(enfrentamiento);
                }

                // Rotar los bots para la siguiente jornada
                if (numBots > 1) {
                    BotEntity lastBot = bots.remove(numBots - 1);
                    bots.add(1, lastBot);
                }
            }

        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Error al iniciar la liga: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al iniciar la liga: " + e.getMessage());
        }
    }

    //Obtener clasificacion de liga
    @Transactional
    public List<ParticipationResponseDto> getLeaderboardFromLeague(Long leagueId) {

        List<ParticipationResponseDto> participations = participacionRepository.findByLeagueId(leagueId);

        // Ordenamos por puntos descendente
        participations.sort(Comparator.comparingInt(ParticipationResponseDto::getPoints).reversed());

        int position = 1;
        for (ParticipationResponseDto participation : participations) {
            participation.setPosition(position++);
        }

        return participations;
    }

    @Transactional
    public List<MatchResponseDTO> getMatchesFromLeague(Long leagueId) {

        return enfrentamientoRepository.findByLeagueId(leagueId);

    }
}