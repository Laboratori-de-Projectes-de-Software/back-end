package com.example.back_end_eing.controllers;


import com.example.back_end_eing.constants.EstadoLigaConstants;
import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;
import com.example.back_end_eing.services.LigaService;
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

//    @GetMapping("/clasificacion")
//    public ResponseEntity<List<Clasificacion>> obtenerClasificacion(@RequestParam Long liga) {
//        List<Clasificacion> clasificacion = ligaService.LigaClasificacion(liga);
//        return new ResponseEntity<>(clasificacion, HttpStatus.OK);
//    }

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
