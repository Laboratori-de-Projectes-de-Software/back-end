package com.adondeband.back_end_adonde_band.API.enfrentamiento;

import com.adondeband.back_end_adonde_band.API.mensaje.MensajeDTO;
import com.adondeband.back_end_adonde_band.dominio.conversacion.ConversacionService;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoImpl;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v0/match")
public class EnfrentamientoController {

    private final EnfrentamientoService enfrentamientoService;

    @Autowired
    public EnfrentamientoController(EnfrentamientoImpl enfrentamientoService) {
        this.enfrentamientoService = enfrentamientoService;
    }

    @GetMapping("{matchId}/messages")
    public ResponseEntity<List<MensajeDTO>> listarMensajes(@PathVariable Long matchId) {
        // Se obtiene el enfrentamiento, se extrae la conversación y se parsean los mensajes
        if (matchId == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        Enfrentamiento enfrentamiento = enfrentamientoService.obtenerEnfrentamiento(matchId);
        if (enfrentamiento == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        Conversacion conversacion = enfrentamiento.getConversacion();
        if (conversacion == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        String rutaFichero = conversacion.getFicheroRuta();
        if (rutaFichero == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        // obtener json de mensajes del fichero
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Para manejar LocalDateTime

            // Leer y mapear el JSON al objeto Java
            Mensajes mensajesFichero = objectMapper.readValue(new File(rutaFichero), Mensajes.class);

            // Convertir los mensajes a MensajeDTO
            List<MensajeDTO> mensajesDTO = new ArrayList<>();
            for (Mensajes.Mensaje mensaje : mensajesFichero.getMsgsL()) {
                mensajesDTO.add(new MensajeDTO(mensaje.getMensaje(), null, mensaje.getTimestamp()));
            }
            for (Mensajes.Mensaje mensaje : mensajesFichero.getMsgsV()) {
                mensajesDTO.add(new MensajeDTO(mensaje.getMensaje(), null, mensaje.getTimestamp()));
            }

            return ResponseEntity.status(HttpStatus.OK).body(mensajesDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
