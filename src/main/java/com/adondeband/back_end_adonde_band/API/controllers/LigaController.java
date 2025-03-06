package com.adondeband.back_end_adonde_band.API.controllers;

import com.adondeband.back_end_adonde_band.API.DTO.LigaDTO;
import com.adondeband.back_end_adonde_band.dominio.services.LigaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/liga")
public class LigaController {

    private final LigaService ligaService;

    public LigaController(LigaService ligaService) {

        this.ligaService = ligaService;
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hello, World!";
    }

    @GetMapping("/insertarBot")
    public ResponseEntity<?> insertarLiga(@RequestParam String nombre,
                      @RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) {
        try {
            LigaDTO ligaDTO = new LigaDTO(nombre, fechaInicio, fechaFin);
            //LigaDTO createdLiga = ligaService.insertarLiga(ligaDTO);
            return null; // ResponseEntity.ok(createdLiga);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Nombre ya elegido");
        }
    }

}
