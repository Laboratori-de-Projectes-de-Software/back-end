package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.dominio.liga.LigaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/liga")
public class LigaController {

    private final LigaImpl ligaImpl;
    private final LigaDtoMapper ligaDtoMapper;

    public LigaController(LigaImpl ligaImpl, LigaDtoMapper ligaDtoMapper) {
        this.ligaImpl = ligaImpl;
        this.ligaDtoMapper = ligaDtoMapper;
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hello, World!";
    }

    @GetMapping("/insertarLiga")
    public ResponseEntity<?> insertarLiga(@RequestParam String nombre,
                                          @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // yyyy-MM-dd'T'HH:mm:ss
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio.trim(), formatter);
            LocalDateTime fin = LocalDateTime.parse(fechaFin.trim(), formatter);

            LigaDTO ligaDTO = new LigaDTO(nombre, inicio, fin);
            LigaDTO createdLiga = ligaDtoMapper.toDTO(ligaImpl.crearLiga(ligaDtoMapper.toDomain(ligaDTO)));
            return ResponseEntity.ok(createdLiga);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Nombre ya elegido");
        }
    }

}
