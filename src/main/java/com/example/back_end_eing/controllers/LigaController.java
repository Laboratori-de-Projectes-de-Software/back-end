package com.example.back_end_eing.controllers;

import com.example.back_end_eing.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Liga")
public class LigaController {

    @Autowired
    private LigaService ligaService;

    @PostMapping("/Registrar")
    public ResponseEntity<String> registrarLiga(@RequestParam String nombreLiga,
                                                @RequestParam Integer numJornadas,
                                                @RequestParam Integer numBots,
                                                @RequestParam String estado,
                                                @RequestParam Integer jornadaActual,
                                                @RequestParam Long id) {
        try {
            ligaService.LigaRegistro(nombreLiga, numJornadas, numBots, estado, jornadaActual, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Liga registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la liga" + e.getMessage());
        }
    }
}
