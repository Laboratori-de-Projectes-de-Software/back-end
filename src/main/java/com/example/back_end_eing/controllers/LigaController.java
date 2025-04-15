package com.example.back_end_eing.controllers;


import com.example.back_end_eing.dto.BotRequestDTO;
import com.example.back_end_eing.exceptions.BotAlreadyRegisteredException;
import com.example.back_end_eing.exceptions.BotLimitReachedException;
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

    @PutMapping
    public ResponseEntity<LeagueResponseDTO> registrarLiga(@RequestBody LeagueDTO leagueDTO) {
        String urlImagenCloudinary;
        if (leagueDTO.getUrlImagen() == null || leagueDTO.getUrlImagen().isEmpty()) {
            urlImagenCloudinary = null;
        } else {
            try {
                urlImagenCloudinary = cloudinaryService.uploadBase64(leagueDTO.getUrlImagen());
            } catch (IOException e) {
                return ResponseEntity.status(500).body(new LeagueResponseDTO());
            }
        }

        try {

            LeagueResponseDTO responseDTO = ligaService.LigaRegistro(leagueDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LeagueResponseDTO());
        }
    }

    @GetMapping("/all")
    public List<LeagueResponseDTO> obtenerLigas() {
        return ligaService.obtenerLigas();
    }

    @GetMapping
    public List<LeagueResponseDTO> obtenerLigasByUserId(@RequestParam Long owner) {
        return ligaService.obtenerLigasByUserId(owner);
    }


    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> getLiga(@PathVariable Long leagueId) {

        LeagueResponseDTO liga = ligaService.getLiga(leagueId);

        return new ResponseEntity<>(liga, HttpStatus.OK);

    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> deleteLiga(@PathVariable Long leagueId) {

        LeagueResponseDTO responseDTO = ligaService.deleteLiga(leagueId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipationResponseDTO>> obtenerClasificacion(@PathVariable Long leagueId) {

        List<ParticipationResponseDTO> clasificacion = ligaService.getClasificacion(leagueId);

        return new ResponseEntity<>(clasificacion, HttpStatus.OK);
    }

    @PostMapping("/{leagueId}/start")
    public ResponseEntity<String> generarEnfrentamientos(@PathVariable Long leagueId) {
        try {
            enfrentamientoService.generarEnfrentamientos(leagueId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Enfrentamientos generados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al generar enfrentamientos" + e.getMessage());
        }
    }

    @PostMapping("/{leagueId}/bot")
    public ResponseEntity<String> registrarBotEnLiga(@PathVariable Long leagueId,
                                                     @RequestBody Long botId) {

        try {
            ligaService.registerBotToLeague(botId, leagueId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bot registrado correctamente");
        } catch (BotAlreadyRegisteredException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (BotLimitReachedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());

        }
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> actualizarLiga(@RequestBody LeagueDTO leagueDTO,
                                                @PathVariable Long leagueId) {
        String urlImagenCloudinary;
        if (leagueDTO.getUrlImagen() == null || leagueDTO.getUrlImagen().isEmpty()) {
            urlImagenCloudinary = null;
        } else {
            try {
                urlImagenCloudinary = cloudinaryService.uploadBase64(leagueDTO.getUrlImagen());
            } catch (IOException e) {
                return ResponseEntity.status(500).body(new LeagueResponseDTO());
            }
        }

        try {
            ligaService.actualizarLiga(leagueDTO, leagueId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new LeagueResponseDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LeagueResponseDTO());
        }
    }

    @GetMapping("/{leagueId}/owner")
    public ResponseEntity<Long> getLigaOwner(@PathVariable Long leagueId) {

        Long owner = ligaService.getOwnerByLeagueId(leagueId);
        return new ResponseEntity<>(owner, HttpStatus.OK);

    }


}
