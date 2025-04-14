package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.gironetaServer.application.services.BotService;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotSummaryDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserResponse;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.BotSummaryMapper;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.BotMapper;

@RestController
@RequestMapping("/api/v0")
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/bot")
    public ResponseEntity<BotResponseDTO> createBot(@RequestBody BotDto botDto) {
        Bot bot = BotMapper.toAppObject(botDto);
        Bot savedBot = botService.createBot(bot);
        return new ResponseEntity<>(BotMapper.toBotResponseDto(savedBot), HttpStatus.CREATED);
    }

    @GetMapping("/bot/{botId}")
    public ResponseEntity<BotResponseDTO> getBot(@PathVariable Long botId) {
        Optional<Bot> botOptional = botService.getBotById(botId);

        if (botOptional.isPresent()) {
            Bot bot = botOptional.get();
            return ResponseEntity.ok(BotMapper.toBotResponseDto(bot));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // /bot?owner={userid}
    @GetMapping("/bot")
    public ResponseEntity<List<BotSummaryDto>> getBots(@RequestParam(name = "owner", required = false) Long userId) {
        List<Bot> bots;

        if (userId != null) {
            bots = botService.getBotsByOwner(userId);
            if (bots.isEmpty()) {
                return ResponseEntity.notFound().build(); // Devuelve 404 si no se encuentran bots para el usuario
            }
        } else {
            bots = botService.getAllBots();
            if (bots.isEmpty()) {
                return ResponseEntity.notFound().build(); // Devuelve 404 si no hay ningún bot en la base de datos
            }
        }

        List<BotSummaryDto> botSummaryDTOs = bots.stream()
                .map(BotSummaryMapper::toBotSummaryDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(botSummaryDTOs);
    }



    @PutMapping("/bot/{botId}")
    public ResponseEntity<BotResponseDTO> updateBot(@PathVariable Long botId, @RequestBody BotDto botDto) {
        Bot bot = BotMapper.toAppObject(botDto);
        Bot savedBot = botService.updateBot(botId,bot);
        return ResponseEntity.ok(BotMapper.toBotResponseDto(savedBot));
    }

}
