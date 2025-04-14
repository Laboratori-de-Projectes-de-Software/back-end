package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.EnfrentamientoDTO;
import com.example.back_end_eing.models.Enfrentamiento;
import com.example.back_end_eing.services.EnfrentamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/match")  // Added prefix to match frontend base URL
public class EnfrentamientoController {

    @Autowired
    private EnfrentamientoService enfrentamientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Enfrentamiento> getEnfrentamientoById(@PathVariable("id") Long id) {
    Enfrentamiento enfrentamiento = enfrentamientoService.obtenerEnfrentamientoConParticipaciones(id);
    return new ResponseEntity<>(enfrentamiento, HttpStatus.OK);
}

    @GetMapping("/{matchId}/message")
    public ResponseEntity<EnfrentamientoDTO> obtenerConversacion(@PathVariable("matchId") Long matchId) {
        EnfrentamientoDTO conversacion = enfrentamientoService.obtenerConversacion(matchId);
        return new ResponseEntity<>(conversacion, HttpStatus.OK);
    }
}