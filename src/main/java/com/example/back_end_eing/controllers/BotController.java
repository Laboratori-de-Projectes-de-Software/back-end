package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.BotDTO;
import com.example.back_end_eing.services.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping("/api/v0/bot")
    public ResponseEntity<String> registrarBot(@RequestParam String nombre,
                                            @RequestParam String descripcion,
                                            @RequestParam String foto,
                                            @RequestParam String API,
                                            @RequestParam int id) {
        try {
            BotDTO botdto = new BotDTO(nombre, descripcion, foto, API, id);
            botService.BotRegistro(botdto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bot registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el bot: " + e.getMessage());
        }
    }
}
