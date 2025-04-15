package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.enfrentamiento.EnfrentamientoDTO;
import com.adondeband.back_end_adonde_band.API.enfrentamiento.EnfrentamientoDtoMapper;
import com.adondeband.back_end_adonde_band.API.participacion.ParticipacionDTO;
import com.adondeband.back_end_adonde_band.API.participacion.ParticipacionDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.exception.BotAlreadyParticipatesException;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class LigaController {

    // Services
    private final LigaService ligaService;
    private final UsuarioService usuarioService;
    private final ImagenService imagenService;
    private final EnfrentamientoService enfrentamientoService;
    private final BotService botService;

    // Mappers
    private final LigaDtoMapper ligaDtoMapper;
    private final ParticipacionDtoMapper participacionDtoMapper;
    private final EnfrentamientoDtoMapper enfrentamientoDtoMapper;


    @Autowired
    public LigaController(LigaImpl ligaService, LigaDtoMapper ligaDtoMapper, ParticipacionDtoMapper participacionDtoMapper,
                          UsuarioService usuarioService, ImagenService imagenService,
                          EnfrentamientoService enfrentamientoService, BotService botService, EnfrentamientoDtoMapper enfrentamientoDtoMapper) {
        this.ligaService = ligaService;
        this.ligaDtoMapper = ligaDtoMapper;
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
        this.enfrentamientoService = enfrentamientoService;
        this.botService = botService;
        this.participacionDtoMapper = participacionDtoMapper;
        this.enfrentamientoDtoMapper = enfrentamientoDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<LigaResponseDTO>> listarLigas(@RequestParam(value = "owner", required = false) Long userId) {
        // TODO
        // Obtener listado de ligas
        List<Liga> ligas;
        try {
            ligas = (userId != null)
                    ? ligaService.obtenerLigasPorUsuario(new UsuarioId(userId))
                    : ligaService.obtenerTodasLasLigas();
        } catch (NotFoundException e) {
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
    public ResponseEntity<LigaResponseDTO> obtenerLiga(@PathVariable Long leagueId) {
        Liga liga = ligaService.obtenerLigaPorId(new LigaId(leagueId));

        if (liga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        LigaResponseDTO ligaDTO = ligaDtoMapper.toDTO(liga);

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
    public ResponseEntity<?> addBotToLiga(@PathVariable Long leagueId, @RequestBody String botId) {
        try {
            ligaService.addBotToLiga(new LigaId(leagueId), new BotId(Long.parseLong(botId)));
            return ResponseEntity.status(HttpStatus.CREATED).body("Bot " + botId + " añadido");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (BotAlreadyParticipatesException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipacionDTO>> obtenerClasificacion(@PathVariable Long leagueId) {
        // obtener Participaciones Por liga
        List <Participacion> participaciones;
        try {
            participaciones = ligaService.obtenerParticipacionesPorLiga(new LigaId(leagueId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List <ParticipacionDTO> participacionesDTO = new ArrayList<>(participaciones.size());

        // Si la lista está vacía, no hay participaciones
        if (!participaciones.isEmpty()) {
            List <BotId> botIds =  participaciones.
                    stream().
                    map(Participacion::getBot).
                    toList();

            List <String> botNames = botService.getBotNames(botIds);

            // Map Participacion to ParticipacionDto
            for (int i = 0; i < participaciones.size(); i++) {
                ParticipacionDTO pDto = participacionDtoMapper.toDTO(participaciones.get(i));
                pDto.setBotName(botNames.get(i));

                participacionesDTO.add(pDto);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(participacionesDTO);
    }

    @PostMapping("/{leagueId}/start")
    public ResponseEntity<?> comenzarLiga(@PathVariable Long leagueId) {
        System.out.println("Comenzando liga con id: " + leagueId);
        //Obtener liga
        Liga liga = ligaService.obtenerLigaPorId(new LigaId(leagueId));
        // Comprobar si la liga existe
        if (liga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (liga.getEstado().equals(ESTADO.FINALIZADO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Liga ya finalizada");
        }

        if (liga.getEstado().equals(ESTADO.EN_CURSO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Liga ya iniciada");
        }

        // Comprobar si la liga tiene participantes suficientes
        int numParticipaciones = liga.getParticipaciones().size();
        if(numParticipaciones < 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay suficientes participantes");
        }

        // Crear Enfrentamientos
        List<Participacion> participaciones = ligaService.obtenerParticipacionesPorLiga(liga.getId());
        List <EnfrentamientoId> enfrentamientosId = enfrentamientoService.crearEnfrentamientosLiga(participaciones, liga.getId());

        liga = ligaService.startLiga(liga, enfrentamientosId);

        return ResponseEntity.status(HttpStatus.OK).body("Liga iniciada");
    }

    @GetMapping("/{leagueId}/match")
    public ResponseEntity<List<EnfrentamientoDTO>> obtenerEnfrentamientos(@PathVariable Long leagueId) {

        // Obtener la liga
        Liga liga = ligaService.obtenerLigaPorId(new LigaId(leagueId));
        // Comprobar si la liga existe

        if (liga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //Obtener la lista de enfrentamientos de la liga
        List<Enfrentamiento> enfrentamientos = enfrentamientoService.obtenerEnfrentamientosPorLiga(new LigaId(leagueId));

        // Convertir a EnfrentamientoDTO
        List<EnfrentamientoDTO> enfrentamientosDTO = new ArrayList<>();
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            enfrentamientosDTO.add(enfrentamientoDtoMapper.toDTO(enfrentamiento));
        }
        return ResponseEntity.status(HttpStatus.OK).body(enfrentamientosDTO);
    }
}
