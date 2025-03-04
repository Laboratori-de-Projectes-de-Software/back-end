package com.adondeband.back_end_adonde_band.api.controllers;

import com.adondeband.back_end_adonde_band.api.DTO.BotDTO;
import com.adondeband.back_end_adonde_band.api.modelos.Bot;
import com.adondeband.back_end_adonde_band.services.BotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hello, World!";
    }

    @GetMapping("/insertarBot")
    public ResponseEntity<?> insertarBot(@RequestParam String nombre, @RequestParam String defensa) {
        try {
            BotDTO botDTO = new BotDTO(nombre, defensa);
            BotDTO createdBot = botService.insertarBot(botDTO);
            return ResponseEntity.ok(createdBot);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Nombre ya elegido");
        }
    }

}
