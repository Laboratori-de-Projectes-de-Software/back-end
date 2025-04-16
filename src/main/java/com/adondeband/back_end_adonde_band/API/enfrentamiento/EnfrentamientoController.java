package com.adondeband.back_end_adonde_band.API.enfrentamiento;

import com.adondeband.back_end_adonde_band.API.mensaje.MensajeDTO;
import com.adondeband.back_end_adonde_band.API.mensaje.MensajeDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.conversacion.ConversacionService;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoImpl;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v0/match")
public class EnfrentamientoController {

    private final EnfrentamientoService enfrentamientoService;
    private final ConversacionService conversacionService;
    private final MensajeDtoMapper mensajeDtoMapper;

    //DEBUG
    private final BotService botService;
    private final EnfrentamientoDtoMapper enfrentamientoDtoMapper;

    @Autowired
    public EnfrentamientoController(EnfrentamientoImpl enfrentamientoService, ConversacionService conversacionService,
                                    MensajeDtoMapper mensajeDtoMapper, BotService botService, EnfrentamientoDtoMapper enfrentamientoDtoMapper) {
        this.enfrentamientoService = enfrentamientoService;
        this.conversacionService = conversacionService;
        this.mensajeDtoMapper = mensajeDtoMapper;

        // DEBUG
        this.botService = botService;
        this.enfrentamientoDtoMapper = enfrentamientoDtoMapper;
    }

    private void crearConversacion(EnfrentamientoId enfrentamientoId) {
        // Crear y guardar conversación
        Conversacion conversacion = new Conversacion();
        conversacion.setFicheroRuta("src/test/java/com/adondeband/back_end_adonde_band/dominio/enfrentamiento/conv.json");
        Conversacion conversacionSaved = conversacionService.crearConversacion(conversacion);

        // Guardar enfrentamiento
        //Enfrentamiento enfrentamientoSaved =
        enfrentamientoService.actualizarConversacion(enfrentamientoId, conversacionSaved);

        //ResponseEntity.status(HttpStatus.OK).body(enfrentamientoDtoMapper.toDTO(enfrentamientoSaved));
    }

    @GetMapping("/{matchId}/message")
    public ResponseEntity<List<MensajeDTO>> listarMensajes(@PathVariable Long matchId) {
        // Se obtiene el enfrentamiento, se extrae la conversación y se parsean los mensajes
        if (matchId == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        // ***** PROVISIONAL *****
        // Se llama a testInit para enlazar el enfrentamiento con una conversación
        crearConversacion(new EnfrentamientoId(matchId));
        // ***** PROVISIONAL *****

        // Obtener enfrentamiento
        List<Enfrentamiento> enfrentamientos = enfrentamientoService.obtenerEnfrentamiento(matchId);
        if (enfrentamientos.isEmpty()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        Conversacion conversacion = enfrentamientos.getFirst().getConversacion();
        if (conversacion == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        String rutaFichero = conversacion.getFicheroRuta();
        if (rutaFichero == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}

        // Obtener mensajes a partir de fichero
        Mensajes mensajesFichero = conversacionService.obtenerMensajes(rutaFichero);

        // Convertir los mensajes a MensajeDTO
        List<MensajeDTO> mensajesDTO = mensajeDtoMapper.toListDTO(mensajesFichero);

        return ResponseEntity.status(HttpStatus.OK).body(mensajesDTO);
    }
}
