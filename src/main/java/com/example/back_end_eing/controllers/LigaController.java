package com.example.back_end_eing.controllers;


import com.example.back_end_eing.services.EnfrentamientoService;

import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;

import com.example.back_end_eing.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class LigaController {

    @Autowired
    private LigaService ligaService;
    private EnfrentamientoService enfrentamientoService;



    @PostMapping
    public ResponseEntity<String> registrarLiga(@RequestParam String nombreLiga,
                                                @RequestParam String urlImagen,
                                                @RequestParam Integer numJornadas,
                                                @RequestParam long matchTime,
                                                @RequestParam Integer numBots,
                                                @RequestParam int id) {
        LeagueDTO ligadto = new LeagueDTO(nombreLiga, urlImagen, numJornadas, matchTime, numBots, id);
        ligaService.LigaRegistro(ligadto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Liga registrada correctamente");
    }

    @GetMapping("/all")
    public List<LeagueResponseDTO> obtenerLigas() {
        return ligaService.obtenerLigas();
    }

    //ESTE MÉODO NO PUEDE DEVOLVER LA ENTITY, HAY QUE PASAR EL DTO
//    @GetMapping
//    public List<Liga> obtenerLigasUser(Long id) {
//        return ligaService.obtenerLigasUser(id);
//    }


    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueResponseDTO> getLiga(@PathVariable Long leagueId){

        LeagueResponseDTO liga = ligaService.getLiga(leagueId);

        return new ResponseEntity<>(liga, HttpStatus.OK);

    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<Void> deleteLiga(@PathVariable Long leagueId){

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
}
