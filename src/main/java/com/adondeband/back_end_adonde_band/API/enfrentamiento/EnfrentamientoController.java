package com.adondeband.back_end_adonde_band.API.enfrentamiento;

import com.adondeband.back_end_adonde_band.API.liga.LigaDTO;
import com.adondeband.back_end_adonde_band.API.liga.LigaDtoMapper;
import com.adondeband.back_end_adonde_band.API.mensaje.MensajeDTO;
import com.adondeband.back_end_adonde_band.API.participacion.ParticipacionDTO;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoImpl;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaImpl;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaService;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionService;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class EnfrentamientoController {

    private final EnfrentamientoService enfrentamientoService;

    @Autowired
    public EnfrentamientoController(EnfrentamientoImpl enfrentamientoService) {
        this.enfrentamientoService = enfrentamientoService;
    }

    @GetMapping
    public ResponseEntity<List<MensajeDTO>> listarMensajes(@RequestParam Long idPartido) {
        // TODO
        if (idPartido == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}
        //List<String> mensajes = enfrentamientoService.obtenerMensajesEnfrentamiento(idPartido);

        // Pasar de Mensaje a MensajeDTO
        //List<MensajeDTO> mensajesDTO = mensajes.stream().map(MensajeDTO::new).toList();

        //return ResponseEntity.status(HttpStatus.OK).body(mensajesDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
