package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.application.services.LeagueService;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.TimeoutException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LigaDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.LigaMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0")
public class LeagueController {

    private final LeagueService leagueService;
    private final LigaMapper ligaMapper;

    public LeagueController(LeagueService leagueService, LigaMapper ligaMapper) {
        this.leagueService = leagueService;
        this.ligaMapper = ligaMapper;
    }

    @PostMapping("/league")
    public ResponseEntity<?> createLeague(@RequestBody LigaDto leagueDto) {
        try {
            League league = LigaMapper.toAppObject(leagueDto);
            League savedLeague = leagueService.createLeague(league);
            return ResponseEntity.status(HttpStatus.CREATED).body(ligaMapper.toLeagueDto(savedLeague));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "No tienes permisos para crear esta liga: " + e.getMessage()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found",
                            "No se encontró un recurso necesario para crear esta liga: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponseDto("Conflict",
                            "Ya existe una liga con el mismo nombre: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto("Bad Request", "Datos de liga inválidos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al crear la liga: " + e.getMessage()));
        }
    }

    @GetMapping("/league")
    public ResponseEntity<?> getAllLeagues(@RequestParam(required = false) Long owner) {
        try {
            List<League> leagues = leagueService.getAllLeagues(owner);
            List<LigaDto> leagueDtos = leagues.stream()
                    .map(ligaMapper::toLeagueDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(leagueDtos);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "No tienes permisos para obtener las ligas: " + e.getMessage()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found", "El usuario especificado no existe: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al obtener las ligas: " + e.getMessage()));
        }
    }

    @GetMapping("/league/{id}")
    public ResponseEntity<?> getLeagueById(@PathVariable Long id) {
        try {
            League league = leagueService.getLeagueById(id);
            LigaDto leagueDto = ligaMapper.toLeagueDto(league);
            return ResponseEntity.ok(leagueDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found",
                            "No se encontró la liga con el ID proporcionado: " + e.getMessage()));
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponseDto("Forbidden",
                            "No tienes permisos para acceder a esta liga: " + e.getMessage()));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "Se requiere autenticación para acceder a esta liga: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto("Bad Request",
                            "El ID de liga proporcionado no es válido: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al obtener la liga: " + e.getMessage()));
        }
    }

    @PutMapping("/league/{id}")
    public ResponseEntity<?> updateLeague(@PathVariable Long id, @RequestBody LigaDto leagueDto) {
        try {
            League league = LigaMapper.toAppObject(leagueDto);
            League updatedLeague = leagueService.updateLeague(id, league);
            LigaDto updatedLeagueDto = ligaMapper.toLeagueDto(updatedLeague);
            return ResponseEntity.ok(updatedLeagueDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found",
                            "No se encontró la liga que se intentó actualizar: " + e.getMessage()));
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponseDto("Forbidden",
                            "No tienes permisos para actualizar esta liga: " + e.getMessage()));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "Se requiere autenticación para actualizar esta liga: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto("Bad Request",
                            "Los datos proporcionados para actualizar la liga no son válidos: " + e.getMessage()));
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponseDto("Conflict",
                            "Ya existe otra liga con el mismo nombre: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al actualizar la liga: " + e.getMessage()));
        }
    }

    @PostMapping("/league/{id}/bot")
    public ResponseEntity<?> registerBot(@PathVariable Long id, @RequestBody Map<String, Long> request) {
        try {
            leagueService.registerBotToLeague(id, request.get("botId"));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found",
                            "No se encontró la liga o el bot especificado: " + e.getMessage()));
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponseDto("Forbidden",
                            "No tienes permisos para registrar bots en esta liga: " + e.getMessage()));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "Se requiere autenticación para registrar un bot en esta liga: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto("Bad Request",
                            "Los datos proporcionados para registrar el bot no son válidos: " + e.getMessage()));
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponseDto("Conflict",
                            "El bot ya está registrado en esta liga: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al registrar el bot en la liga: " + e.getMessage()));
        }
    }

    @DeleteMapping("/league/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable Long leagueId) {
        try {
            League league = leagueService.getLeagueById(leagueId);
            LigaDto leagueDto = ligaMapper.toLeagueDto(league);
            leagueService.deleteLeague(leagueId);
            return ResponseEntity.ok(leagueDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found",
                            "No se encontró la liga que se intentó eliminar: " + e.getMessage()));
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponseDto("Forbidden",
                            "No tienes permisos para eliminar esta liga: " + e.getMessage()));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "Se requiere autenticación para eliminar esta liga: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto("Bad Request",
                            "El ID de liga proporcionado no es válido: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al eliminar la liga: " + e.getMessage()));
        }
    }
}