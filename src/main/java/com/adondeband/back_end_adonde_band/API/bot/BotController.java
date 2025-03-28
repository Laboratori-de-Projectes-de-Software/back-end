package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.bot.BotImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bot")
public class BotController {

    private final BotService botService;
    private final BotDtoMapper botMapper;

    @Autowired
    public BotController(BotImpl botService, BotDtoMapper botDtoMapper) {
        this.botService = botService;
        this.botMapper = botDtoMapper;
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hello, World!";
    }

    @PostMapping("/crearbot")
    public ResponseEntity<BotDTO> crearBot(@RequestBody BotDTO botDTO) {

        Bot bot = botMapper.toDomain(botDTO);
        Bot nuevoBot = botService.crearBot(bot);
        return ResponseEntity.status(HttpStatus.CREATED).body(botMapper.toDTO(nuevoBot));
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<List<BotDTO>> obtenerBot(@PathVariable String nombre) {
        // Obtener la lista de Bots desde el servicio
        List<Bot> bots = botService.obtenerBotPorNombre(nombre);

        // Convertir manualmente cada Bot a BotDTO
        List<BotDTO> botsDTO = new ArrayList<>();
        for (Bot bot : bots) {
            botsDTO.add(botMapper.toDTO(bot));
        }

        // Devolver la lista de BotDTO en la respuesta HTTP
        return ResponseEntity.ok(botsDTO);
    }
}
