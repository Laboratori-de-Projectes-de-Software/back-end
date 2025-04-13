package com.debateia.adapter.in.web;

import com.debateia.adapter.in.web.dto.request.BotDTO;
import com.debateia.adapter.in.web.dto.response.BotResponseDTO;
import com.debateia.adapter.in.web.dto.response.BotSummaryResponseDTO;
import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.application.jwt.JwtService;
import com.debateia.application.mapper.BotMapper;
import com.debateia.application.service.BotService;
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

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {
    private final BotService botService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<BotSummaryResponseDTO>> getAllBots(
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        List<Bot> bots;
        try {
            bots = botService.getBots(Optional.ofNullable(ownerId));
        } catch (EntityNotFoundException e) { // usuario inexistente (del que se pide obtener bots)
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<BotSummaryResponseDTO> response = bots.stream()
                .map(BotMapper::toSummaryDTO)
                .toList(); // conversion a DTO muy prog funcional :3
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BotResponseDTO> createBot(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @RequestBody BotDTO botDto) {
        Bot botIn = BotMapper.DTOtoDomain(botDto);
        Bot bot;
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtService.extractUserId(authToken);
        try {
            bot = botService.createBot(botIn, userId); // meter en BD si el usuario existe
        } catch (DataIntegrityViolationException e) { // bot ya existia
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e) { // anadir bot para usuario que no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        BotResponseDTO botResponse = BotMapper.toResponseDto(bot);
        return ResponseEntity.status(HttpStatus.CREATED).body(botResponse);
    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> getBotById(
            @PathVariable Integer botId) {
        try {
            Bot bot = botService.getBotById(botId);
            return ResponseEntity.ok(BotMapper.toResponseDto(bot));
        } catch (EntityNotFoundException e) { // bot no encontrado
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> updateBot(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @PathVariable Integer botId,
            @RequestBody BotDTO botDto) {
        Bot botIn = BotMapper.DTOtoDomain(botDto);
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final Integer userId = jwtService.extractUserId(authToken);
        try {
            Bot botUpdate = botService.updateBot(botId, userId, botIn);
            BotResponseDTO response = BotMapper.toResponseDto(botUpdate);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) { // bot/usuario asociado no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) { // usuario (es unico) seria repetido tras actualizar
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}