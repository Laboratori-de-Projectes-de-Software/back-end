package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.application.services.LeagueService;
import com.example.gironetaServer.application.services.MatchService;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.domain.exceptions.*;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.*;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.LigaMapper;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.IllegalArgumentException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    //Obtener enfrenamientos (pasados, presentes y futuros)
    @GetMapping("/match/{matchId}/message")
    public ResponseEntity<?> getMensajesFromEnfrentamiento(@PathVariable Long matchId) {
        try {
            // Obtenemos la lista de participaciones (leaderboard)
            List<MessageResponseDTO> messages = matchService.getMessagesFromEnfrentamiento(matchId);

            if(messages.isEmpty()) {
                throw new ResourceNotFoundException("No existen mensajes para dicho enfrentamiento");
            }
            return ResponseEntity.ok(messages);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found",
                            "No se encontraron los mensajes para dicho enfrentamiento: " + e.getMessage()));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized",
                            "Se requiere autenticación para mirar los mensajes: " + e.getMessage()));
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ErrorResponseDto("Request Timeout",
                            "La operación excedió el tiempo límite: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDto("Bad Request",
                            "El ID de enfrentamiento proporcionado no es válido: " + e.getMessage()));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error",
                            "Ocurrió un error inesperado al obtener los mensajes: " + e.getMessage()));
        }
    }
}