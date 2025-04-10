package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;
import com.example.back_end_eing.services.BotService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/bot")
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping()
    public ResponseEntity<String> registrarBot(@RequestParam String nombre,
                                            @RequestParam String descripcion,
                                            @RequestParam String foto,
                                            @RequestParam int victorias,
                                            @RequestParam int numJornadas,
                                            @RequestParam String API,
                                            @RequestParam Long id) {
        try {
            botService.BotRegistro(nombre, descripcion, foto, victorias, numJornadas, API, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bot registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el bot: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<BotSummaryResponseDTO>> listarBots(@RequestParam Long owner) {
        System.out.println("Listando Bots "+owner);
        return ResponseEntity.ok(botService.listarBots(owner));

    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> obtenerBot(@PathVariable("botId") Long botId) {
        System.out.println("Obteniendo Bots "+botId);
        return ResponseEntity.ok(botService.obtenerBot(botId));

    }


}
