package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.enfrentamiento.EnfrentamientoDTO;
import com.adondeband.back_end_adonde_band.API.participacion.ParticipacionDTO;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenService;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaImpl;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaService;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionService;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class LigaController {

    private final LigaService ligaService;
    private final LigaDtoMapper ligaDtoMapper;
    private final ParticipacionService participacionService;
    private final UsuarioService usuarioService;
    private final ImagenService imagenService;

    @Autowired
    public LigaController(LigaImpl ligaService, LigaDtoMapper ligaDtoMapper, ParticipacionService participacionService, UsuarioService usuarioService, ImagenService imagenService) {
        this.ligaService = ligaService;
        this.ligaDtoMapper = ligaDtoMapper;
        this.participacionService = participacionService;
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;

    }

    @GetMapping
    public ResponseEntity<List<LigaResponseDTO>> listarLigas(@RequestParam(value = "owner", required = false) Long userId) {
        // TODO
        // Obtener listado de ligas
        List<Liga> ligas = null;
        try {
            ligas = (userId != null)
                    ? ligaService.obtenerLigasPorUsuario(new UsuarioId(userId))
                    : ligaService.obtenerTodasLasLigas();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // comprobar que la lista no está vacía
        if (ligas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Pasar de Liga a LigaDTO
        List<LigaResponseDTO> ligasDTO = ligas.stream().map(ligaDtoMapper::toDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(ligasDTO);
    }

    @PostMapping
    public ResponseEntity<LigaResponseDTO> insertarLiga(@RequestBody LigaDTO ligaDTO) {
        // Convertir LigaDTO a Liga
        LigaResponseDTO ligaResponseDTO = new LigaResponseDTO(ligaDTO);
        Liga liga = ligaDtoMapper.toDomain(ligaResponseDTO);

        // Obtiene el usuario autenticado desde SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Obtener el id de usuario autenticado
        Usuario user = usuarioService.obtenerUsuarioPorNombre(authentication.getName());
        // Set UserId en el bot
        liga.setUsuario(user.getId());

        // Guardar imagen del bot (por si no existe)
        Imagen imagenSaved = imagenService.guardarImagen(liga.getImagen());
        liga.setImagen(imagenSaved);

        // Guardar la liga
        Liga nuevaLiga = ligaService.crearLiga(liga);

        // Devolver la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(ligaDtoMapper.toDTO(nuevaLiga));
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<List<LigaResponseDTO>> obtenerLiga(@PathVariable Long leagueId) {
        List<Liga> ligas = ligaService.obtenerLigaPorId(new LigaId(leagueId));

        if (ligas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Convertir a LigaDTO
        List<LigaResponseDTO> ligaDTO = new ArrayList<>();
        ligaDTO.add(ligaDtoMapper.toDTO(ligas.getFirst()));

        // Devolver la lista de LigaDTO en la respuesta HTTP
        return ResponseEntity.status(HttpStatus.OK).body(ligaDTO);
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
        Participacion p = participacionService.insertarParticipacion(new Participacion(botId, Long.parseLong(leagueId)));
        if (p != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir el bot a la liga");
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
