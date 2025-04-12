package com.debateia.adapter.in.web;

import com.debateia.adapter.in.web.dto.request.BotDTO;
import com.debateia.adapter.in.web.dto.response.BotResponseDTO;
import com.debateia.adapter.in.web.dto.response.BotSummaryResponseDTO;
import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.application.service.BotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
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

    @GetMapping
    public ResponseEntity<List<BotSummaryResponseDTO>> getAllBots(
            @RequestParam(name = "owner", required = false) Integer ownerId) {
        List<BotEntity> bots;
        try {
            bots = botService.getBots(Optional.ofNullable(ownerId));
        } catch (EntityNotFoundException e) { // usuario inexistente (del que se pide obtener bots)
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<BotSummaryResponseDTO> response = bots.stream()
                .map(this::convertToSummaryDTO)
                .toList(); // conversion a DTO muy prog funcional :3
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BotResponseDTO> createBot(
            @RequestBody BotDTO botDto) {
        BotEntity botEntity = toEntity(botDto);
        try {
            botEntity = botService.createBot(botEntity, botDto.getUserId()); // meter en BD si el usuario existe
        } catch (DataIntegrityViolationException e) { // bot ya existia
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e) { // anadir bot para usuario que no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDto(botEntity));
    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> getBotById(
            @PathVariable Integer botId) {
        try {
            BotEntity botEntity = botService.getBotById(botId);
            return ResponseEntity.ok(convertToResponseDto(botEntity));
        } catch (EntityNotFoundException e) { // bot no encontrado
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> updateBot(
            @PathVariable Integer botId,
            @RequestBody BotDTO botDto) {
        BotEntity botEntity = toEntity(botDto);
        try {
            BotEntity entityUpdate = botService.updateBot(botId, botDto.getUserId(), botEntity);
            BotResponseDTO response = convertToResponseDto(entityUpdate);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) { // bot/usuario asociado no existe
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) { // usuario (es unico) seria repetido tras actualizar
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
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