package com.debateia.adapter.in.web;

import com.debateia.adapter.in.web.dto.request.BotDTO;
import com.debateia.adapter.in.web.dto.response.BotResponseDTO;
import com.debateia.adapter.in.web.dto.response.BotSummaryResponseDTO;
import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.application.jwt.JwtService;
import com.debateia.application.service.BotService;
import lombok.RequiredArgsConstructor;

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
    private final BotService botService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<BotSummaryResponseDTO>> getAllBots(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate,
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        if (!jwtService.isTokenValid(authenticate)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<BotEntity> bots = botService.getBots(Optional.ofNullable(ownerId));
        List<BotSummaryResponseDTO> response = bots.stream()
                .map(this::convertToSummaryDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BotResponseDTO> createBot(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate,
            @RequestBody BotDTO botDto) {
        System.out.println("EL POST SE EJECUTA OK Y M***** Y J****");
        //if (!jwtService.isTokenValid(authenticate)) {
        //    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        //}
        BotEntity botEntity = toEntity(botDto);
        try {
            botEntity = botService.createBot(botEntity, botDto.getUserId()); // meter en BD si el usuario existe
        } catch (IllegalArgumentException e) { // Bot ya existia
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        if (botEntity == null) { // Caso: se queria anadir un bot para un usuario que no existe
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(convertToResponseDto(botEntity));
    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> getBotById(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate,
            @PathVariable Integer botId) {
        if (!jwtService.isTokenValid(authenticate)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        BotEntity botEntity = botService.getBotById(botId);
        if (botEntity == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity.ok(convertToResponseDto(botEntity));
    }

    @PutMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> updateBot(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticate,
            @PathVariable Integer botId,
            @RequestBody BotDTO botDto) {
        if (!jwtService.isTokenValid(authenticate)) {
            return ResponseEntity.
                    status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        BotEntity botEntity = toEntity(botDto);
        BotResponseDTO response = convertToResponseDto(botService.updateBot(botId, botEntity));
        return ResponseEntity
                .ok(response);
    }

    private BotSummaryResponseDTO convertToSummaryDTO(BotEntity bot) {
        BotSummaryResponseDTO dto = new BotSummaryResponseDTO();
        dto.setName(bot.getName());
        dto.setId(bot.getId());
        dto.setDescription(bot.getDescription());
        return dto;
    }

    private BotEntity toEntity(BotDTO dto) {
        BotEntity entity = new BotEntity();
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setId(null); // id = auto increment
        entity.setUrl_imagen(dto.getUrlImagen());
        entity.setEndpoint(dto.getEndpoint());
        entity.setLosses(0);
        entity.setWins(0);
        entity.setDraws(0);
        return entity;
    }

    private BotResponseDTO convertToResponseDto(BotEntity bot) {
        BotResponseDTO dto = new BotResponseDTO();
        dto.setBotId(bot.getId());
        dto.setName(bot.getName());
        dto.setDescription(bot.getDescription());
        dto.setUrlImage(bot.getUrl_imagen());
        dto.setNLosses(bot.getLosses());
        dto.setNWins(bot.getWins());
        dto.setNDraws(bot.getDraws());
        return dto;
    }
}