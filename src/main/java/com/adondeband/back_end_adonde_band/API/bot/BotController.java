package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.bot.BotImpl;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v0/bot")
public class BotController {

    private final BotService botService;
    private final BotDtoMapper botMapper;

    @Autowired
    public BotController(BotImpl botService, BotDtoMapper botDtoMapper) {
        this.botService = botService;
        this.botMapper = botDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<BotDTO>> listarBots(@RequestParam(value = "owner", required = false) String userId) {
        List<Bot> bots = (userId != null) ? botService.obtenerBotsPorUsuario(new UsuarioId(userId)) : botService.obtenerTodosLosBots();

        // comprobar que la lista no está vacía
        if (bots.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // pasar de Bot a BotDTO
        List<BotDTO> botsDTO = bots.stream().map(botMapper::toDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(botsDTO);
    }

    @PostMapping
    public ResponseEntity<BotDTO> crearBot(@RequestBody BotDTO botDTO) {
        Bot bot = botMapper.toDomain(botDTO);
        Bot nuevoBot = botService.crearBot(bot);
        return ResponseEntity.status(HttpStatus.CREATED).body(botMapper.toDTO(nuevoBot));
    }

    @GetMapping("/{botId}")
    public ResponseEntity<List<BotDTO>> obtenerBot(@PathVariable String botId) {
        // Obtener la lista de Bots desde el servicio
        List<Bot> bots = botService.obtenerBotPorNombre(botId);

        // Convertir manualmente de Bot a BotDTO
        List<BotDTO> botDTO = new ArrayList<>();
        botDTO.add(botMapper.toDTO(bots.getFirst()));

        // Devolver la lista de BotDTO en la respuesta HTTP
        return ResponseEntity.status(HttpStatus.OK).body(botDTO);
    }
}
