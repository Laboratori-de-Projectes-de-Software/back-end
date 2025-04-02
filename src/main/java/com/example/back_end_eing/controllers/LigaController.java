package com.example.back_end_eing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back_end_eing.models.Clasificacion;
import com.example.back_end_eing.services.LigaService;

import java.util.List;

@RestController
@RequestMapping("/liga")
public class LigaController {

    @Autowired
    private LigaService ligaService;

    @PutMapping("/actualizar")
    public ResponseEntity<Void> actualizarClasificacion(@RequestParam Long liga, @RequestParam Long local, @RequestParam Long visitante, @RequestParam String resultado) {
        ligaService.LigaActualización(liga, local, visitante, resultado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clasificacion")
    public ResponseEntity<List<Clasificacion>> obtenerClasificacion(@RequestParam Long liga) {
        List<Clasificacion> clasificacion = ligaService.LigaClasificacion(liga);
        return new ResponseEntity<>(clasificacion, HttpStatus.OK);
    }
}
