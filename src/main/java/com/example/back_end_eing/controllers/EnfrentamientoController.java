package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.EnfrentamientoDTO;
import com.example.back_end_eing.models.Enfrentamiento;
import com.example.back_end_eing.services.EnfrentamientoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class EnfrentamientoController {

    @Autowired
    private EnfrentamientoService enfrentamientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Enfrentamiento> getEnfrentamientoById(@RequestParam Long id) {
        Enfrentamiento enfrentamiento = enfrentamientoService.obtenerEnfrentamiento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{matchId}/message")
    public ResponseEntity<EnfrentamientoDTO> obtenerConversacion(@RequestParam Long id) {
        EnfrentamientoDTO conversacion = enfrentamientoService.obtenerConversacion(id);
        return new ResponseEntity<>(conversacion, HttpStatus.OK);
    }
}
