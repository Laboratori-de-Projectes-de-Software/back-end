package com.example.back_end_eing.controllers;

import com.example.back_end_eing.services.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping("/Bot")
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
}
