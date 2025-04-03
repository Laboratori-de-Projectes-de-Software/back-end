package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.enfrentamiento.EnfrentamientoDTO;
import com.adondeband.back_end_adonde_band.API.participacion.ParticipacionDTO;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaImpl;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class LigaController {

    private final LigaService ligaService;
    private final LigaDtoMapper ligaDtoMapper;

    @Autowired
    public LigaController(LigaImpl ligaService, LigaDtoMapper ligaDtoMapper) {
        this.ligaService = ligaService;
        this.ligaDtoMapper = ligaDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<LigaDTO>> listarLigas(@RequestParam(value = "owner", required = false) String userId) {
        //TODO

        /*
        List<Bot> bots = (userId != null) ? botService.obtenerBotsPorUsuario(userId) : botService.obtenerTodosLosBots();
        List<BotDTO> botsDTO = bots.stream().map(botMapper::toDTO).toList();
        return ResponseEntity.ok(botsDTO);
         */

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @PostMapping
    public ResponseEntity<?> insertarLiga(@RequestParam String nombre,
                                          @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        //TODO
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // yyyy-MM-dd'T'HH:mm:ss
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio.trim(), formatter);
            LocalDateTime fin = LocalDateTime.parse(fechaFin.trim(), formatter);

            LigaDTO ligaDTO = new LigaDTO(nombre, inicio, fin);
            LigaDTO createdLiga = ligaDtoMapper.toDTO(ligaService.crearLiga(ligaDtoMapper.toDomain(ligaDTO)));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLiga);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Nombre ya elegido");
        }
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<List<LigaDTO>> obtenerLiga(@PathVariable String leagueId) {
        //TODO

        /*
        List<Liga> ligas = ligaService.obtenerLiga(leagueId);

        if (ligas.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Convertir a LigaDTO
        List<LigaDTO> ligaDTO = new ArrayList<>();
        ligaDTO.add(ligaDtoMapper.toDTO(ligas.getFirst()));

        // Devolver la lista de LigaDTO en la respuesta HTTP
        return ResponseEntity.status(HttpStatus.OK).body(ligaDTO);

         */

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<List<LigaDTO>> updateLiga(@PathVariable String leagueId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<?> eliminarLiga(@PathVariable String leagueId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @PostMapping("/{leagueId}/bot")
    public ResponseEntity<?> addBotToLiga(@PathVariable String leagueId, @RequestBody String botId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @GetMapping("/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipacionDTO>> obtenerClasificacion(@PathVariable String leagueId, @RequestBody String botId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @PostMapping("/{leagueId}/start")
    public ResponseEntity<?> comenzarLiga(@PathVariable String leagueId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @GetMapping("/{leagueId}/match")
    public ResponseEntity<List<EnfrentamientoDTO>> obtenerEnfrentamientos(@PathVariable String leagueId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
}
