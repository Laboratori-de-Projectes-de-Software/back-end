package com.example.back_end_eing.controllers;


import com.example.back_end_eing.dto.BotRequestDTO;
import com.example.back_end_eing.services.EnfrentamientoService;

import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;
import com.example.back_end_eing.services.CloudinaryService;

import com.example.back_end_eing.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class LigaController {

    @Autowired
    private LigaService ligaService;
    @Autowired
    private EnfrentamientoService enfrentamientoService;


    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<String> registrarLiga(@RequestParam String nombreLiga,
                                                @RequestParam String urlImagen,
                                                @RequestParam Integer numJornadas,
                                                @RequestParam long matchTime,
                                                @RequestParam Integer numBots,
                                                @RequestParam int id) {
        String urlImagenCloudinary;
        if (urlImagen == null || urlImagen.isEmpty()) {
            urlImagenCloudinary = null;
        } else {
            try {
                urlImagenCloudinary = cloudinaryService.uploadBase64(urlImagen);
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Error al subir la imagen a Cloudinary: " + e.getMessage());
            }
        }

        try {
            LeagueDTO ligadto = new LeagueDTO(nombreLiga, urlImagenCloudinary, numJornadas, matchTime, numBots, id);
            ligaService.LigaRegistro(ligadto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Liga registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la liga" + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<LeagueResponseDTO> obtenerLigas() {
        return ligaService.obtenerLigas();
    }


    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> getLiga(@PathVariable Long leagueId) {

        LeagueResponseDTO liga = ligaService.getLiga(leagueId);

        return new ResponseEntity<>(liga, HttpStatus.OK);

    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<Void> deleteLiga(@PathVariable Long leagueId) {

        ligaService.deleteLiga(leagueId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipationResponseDTO>> obtenerClasificacion(@PathVariable Long leagueId) {

        List<ParticipationResponseDTO> clasificacion = ligaService.getClasificacion(leagueId);

        return new ResponseEntity<>(clasificacion, HttpStatus.OK);
    }

    @PostMapping("/{leagueId}/start")
    public ResponseEntity<String> generarEnfrentamientos(@RequestParam Long id) {
        try {
            enfrentamientoService.generarEnfrentamientos(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Enfrentamientos generados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al generar enfrentamientos" + e.getMessage());
        }
    }

    @PostMapping("/{leagueId}/bot")
    public ResponseEntity<String> registrarBotEnLiga(@PathVariable Long leagueId,
                                                     @RequestBody BotRequestDTO body) {


        Long botId = body.getBotId();
        try {
            ligaService.registerBotToLeague(botId, leagueId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bot registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());

        }
    }
}
