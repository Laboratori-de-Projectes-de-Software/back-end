package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.gironetaServer.application.services.BotService;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotSummaryDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserResponse;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.BotSummaryMapper;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.UserMapper;
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
    public ResponseEntity<BotDto> createBot(@RequestBody BotDto botDto) {
        Bot bot = BotMapper.toAppObject(botDto);
        Bot savedBot = botService.createBot(bot);
        return ResponseEntity.ok(BotMapper.toBotDto(savedBot));
    }

    @GetMapping("/bot/{botId}")
    public ResponseEntity<BotDto> getBot(@PathVariable Long botId) {
        Optional<Bot> botOptional = botService.getBotById(botId);

        if (botOptional.isPresent()) {
            Bot bot = botOptional.get();
            return ResponseEntity.ok(BotMapper.toBotDto(bot));
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
        } else {
            bots = botService.getAllBots();
        }

        List<BotSummaryDto> botSummaryDTOs = bots.stream()
                .map(BotSummaryMapper::toBotSummaryDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(botSummaryDTOs);
    }

}
