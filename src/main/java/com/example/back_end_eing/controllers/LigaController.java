package com.example.back_end_eing.controllers;


import com.example.back_end_eing.constants.EstadoLigaConstants;
import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;
import com.example.back_end_eing.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v0")
public class LigaController {

    @Autowired
    private LigaService ligaService;

    @PutMapping("/league/actualizar")
    public ResponseEntity<Void> actualizarClasificacion(@RequestParam Long liga, @RequestParam Long local, @RequestParam Long visitante, @RequestParam String resultado) {
        ligaService.LigaActualización(liga, local, visitante, resultado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/clasificacion")
//    public ResponseEntity<List<Clasificacion>> obtenerClasificacion(@RequestParam Long liga) {
//        List<Clasificacion> clasificacion = ligaService.LigaClasificacion(liga);
//        return new ResponseEntity<>(clasificacion, HttpStatus.OK);
//    }

    @PostMapping("/league")
    public ResponseEntity<String> registrarLiga(@RequestParam String nombreLiga,
                                                @RequestParam String urlImagen,
                                                @RequestParam Integer numJornadas,
                                                @RequestParam long matchTime,
                                                @RequestParam Integer numBots,
                                                @RequestParam int id) {
        try {
            LeagueDTO ligadto = new LeagueDTO(nombreLiga, urlImagen, numJornadas, matchTime, numBots, id);
            ligaService.LigaRegistro(ligadto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Liga registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la liga" + e.getMessage());
        }
    }

    @GetMapping("/league/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> getLiga(@PathVariable Integer leagueId){
        List<Integer> lsit = new ArrayList<>();
        lsit.add(1);lsit.add(2);lsit.add(3);
        LeagueResponseDTO liga = new LeagueResponseDTO(leagueId, EstadoLigaConstants.ABIERTA, "Liga de Barrio",
                "Antonio",null,
                16,100L,lsit);
        return new ResponseEntity<LeagueResponseDTO>(liga, HttpStatus.OK);

    }


    @GetMapping("/league/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipationResponseDTO>> obtenerClasificacion(@PathVariable Long leagueId) {
        System.out.println(leagueId);
        List<ParticipationResponseDTO> bots = new ArrayList<>();

        bots.add(new ParticipationResponseDTO(1, "Elgeneroso", 18, 1));
        bots.add(new ParticipationResponseDTO(2, "Ellisto", 2, 3));
        bots.add(new ParticipationResponseDTO(3, "Laambale", 3, 2));
        bots.add(new ParticipationResponseDTO(4, "elcabron", 0, 4));

        return new ResponseEntity<>(bots, HttpStatus.OK);
    }
}
