package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.gironetaServer.application.services.BotService;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotDto;
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
        System.out.println(savedBot.getUsuario_correo());
        return ResponseEntity.ok(BotMapper.toBotDto(savedBot));
    }

}
