package com.debateia.adapter.in.rest.bot;

import com.debateia.adapter.mapper.BotMapper;
import com.debateia.application.ports.in.rest.BotUseCase;
import com.debateia.application.ports.in.rest.JWTUseCase;
import com.debateia.domain.Bot;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {
    private final BotUseCase botUseCase;
    private final JWTUseCase jwtUseCase;
    private final BotMapper botMapper;

    @GetMapping
    public ResponseEntity<List<BotSummaryResponseDTO>> getAllBots(
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        List<Bot> bots;
        try {
            bots = botUseCase.getBots(Optional.ofNullable(ownerId));
        } catch (EntityNotFoundException e) { // usuario inexistente (del que se pide obtener bots)
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<BotSummaryResponseDTO> response = bots.stream()
                .map(botMapper::toSummaryDTO)
                .toList(); // conversion a DTO muy prog funcional :3
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BotDTO> createBot(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @RequestBody CreateBotDTO createBotDto) {
        Bot botIn = botMapper.DTOtoDomain(createBotDto);
        Bot bot;
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtUseCase.extractUserId(authToken);
        try {
            bot = botUseCase.createBot(botIn, userId); // meter en BD si el usuario existe
        } catch (DataIntegrityViolationException e) { // bot ya existia
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        BotDTO botResponse = botMapper.toResponseDto(bot);
        return ResponseEntity.status(HttpStatus.CREATED).body(botResponse);
    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotDTO> getBotById(
            @PathVariable Integer botId) {
        try {
            Bot bot = botUseCase.getBotById(botId);
            return ResponseEntity.ok(botMapper.toResponseDto(bot));
        } catch (EntityNotFoundException e) { // bot no encontrado
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{botId}")
    public ResponseEntity<BotDTO> updateBot(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @PathVariable Integer botId,
            @RequestBody CreateBotDTO createBotDto) {
        Bot botIn = botMapper.DTOtoDomain(createBotDto);
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtUseCase.extractUserId(authToken);
        try {
            Bot botUpdate = botUseCase.updateBot(botId, userId, botIn);
            BotDTO response = botMapper.toResponseDto(botUpdate);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) { // bot/usuario asociado no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}