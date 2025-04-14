package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.enfrentamiento.EnfrentamientoDTO;
import com.adondeband.back_end_adonde_band.API.participacion.ParticipacionDTO;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenService;
import com.adondeband.back_end_adonde_band.dominio.jornada.Jornada;
import com.adondeband.back_end_adonde_band.dominio.jornada.JornadaId;
import com.adondeband.back_end_adonde_band.dominio.jornada.JornadaService;
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
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v0/league")
public class LigaController {

    private final LigaService ligaService;
    private final LigaDtoMapper ligaDtoMapper;
    private final ParticipacionService participacionService;
    private final UsuarioService usuarioService;
    private final ImagenService imagenService;
    private final EnfrentamientoService enfrentamientoService;
    private final JornadaService jornadaService;

    @Autowired
    public LigaController(LigaImpl ligaService, LigaDtoMapper ligaDtoMapper, ParticipacionService participacionService, UsuarioService usuarioService, ImagenService imagenService, EnfrentamientoService enfrentamientoService, JornadaService jornadaService) {
        this.ligaService = ligaService;
        this.ligaDtoMapper = ligaDtoMapper;
        this.participacionService = participacionService;
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
        this.enfrentamientoService = enfrentamientoService;
        this.jornadaService = jornadaService;
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
    public ResponseEntity<?> addBotToLiga(@PathVariable Long leagueId, @RequestBody String botId) {
        Liga liga = ligaService.addBotToLiga(new LigaId(leagueId), new BotId(botId));
        if (liga != null) {
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
    public ResponseEntity<?> comenzarLiga(@PathVariable Long leagueId) {
        //Obtener liga
        List<Liga> ligas = ligaService.obtenerLigaPorId(new LigaId(leagueId));
        // Comprobar si la liga existe
        if (ligas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // Comprobar si la liga ya ha comenzado o finalizado
        Liga liga = ligas.getFirst();
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

        // Obtener el número de jornadas, enfrentamientos y enfrentamientos por jornada
        int numJornadas = numParticipaciones - 1;
        int numEnfrentamientos = numParticipaciones * (numParticipaciones - 1);
        int numEnfrentamientosPorJornada = numParticipaciones % 2 == 0 ? numParticipaciones / 2 : (numParticipaciones - 1) / 2;

        /*
        // Crear Jornadas
        List<Jornada> jornadaList = new ArrayList<>();
        for(int i = 0; i < numJornadas; i++){
            Jornada jornada = new Jornada();
            jornada.setNumJornada(i+1);
            jornada.setLiga(liga.getId());
            jornada = jornadaService.crearJornada(jornada);
            jornadaList.add(jornada);
        }
        */

        // Crear Enfrentamientos
        List<Participacion> participaciones = ligaService.obtenerParticipacionesPorLiga(new LigaId(leagueId));
        List <Enfrentamiento> enfrentamientos = new ArrayList<>();
        for(int i = 0; i < numParticipaciones; i++){
            for(int j = i + 1; j < numParticipaciones; j++){
                Enfrentamiento enfrentamiento = new Enfrentamiento();
                enfrentamiento.setEstado(ESTADO.PENDIENTE);
                enfrentamiento.setLocal(participaciones.get(i).getBot());
                enfrentamiento.setVisitante(participaciones.get(j).getBot());
                enfrentamiento = enfrentamientoService.insertarEnfrentamiento(enfrentamiento);
                enfrentamientos.add(enfrentamiento);
            }
        }
        Collections.shuffle(enfrentamientos);
        // Obtener una lista de todos los ids de los bots
        List<BotId> botIds = new ArrayList<>();
        for (Participacion participacion : participaciones) {
            botIds.add(participacion.getBot());
        }

        // Asignar enfrentamientos a jornadas
        //for(Jornada jornada : jornadaList){
        for (int i = 1; i <= numJornadas; i++) {
            // Lista para los enfrentamientos de esta jornada
            List<EnfrentamientoId> enfrentamientoIds = new ArrayList<>();
            // Copia de la lista de ids de bots para no repetir bots en esta jornada
            List <BotId> botsJornada = new ArrayList<>(botIds);
            // Asignar enfrentamientos a la jornada hasta que esten todos
            while(!enfrentamientos.isEmpty() && enfrentamientoIds.size() != numEnfrentamientosPorJornada){
                // Obtener un enfrentamiento aleatorio de la lista de enfrentamientos
                Enfrentamiento enfrentamiento = enfrentamientos.getFirst();
                // Comprobar si los bots del enfrentamiento ya han sido asignados a la jornada
                int j = 0;
                while(j != enfrentamientos.size() && !(botsJornada.contains(enfrentamiento.getLocal()) && botsJornada.contains(enfrentamiento.getVisitante()))){
                    // Si uno de los dos ya ha sido asignado a la jornada, se busca otro enfrentamiento
                    enfrentamiento = enfrentamientos.get(j);
                    j++;
                }
                // Eliminar bot local y visitante de posibles contricantes
                botsJornada.remove(enfrentamiento.getLocal());
                botsJornada.remove(enfrentamiento.getVisitante());
                // Eliminar enfrentamiento de la lista de enfrentamientos
                enfrentamientos.remove(enfrentamiento);

                // Asignar jornada al enfrentamiento
                // enfrentamiento.setJornada(jornada.getId());

                // TODO: DESCOMENTAR enfrentamiento.setRonda(i);

                // Actualizar enfrentamiento
                enfrentamientoService.insertarEnfrentamiento(enfrentamiento);
                // Añadir enfrentamiento a la lista de ids de enfrentamientos de la jornada
                enfrentamientoIds.add(enfrentamiento.getId());
            }
            /*
            // Asignar enfrentamientos a la jornada
            jornada.setEnfrentamientos(enfrentamientoIds);
            // Actualizar jornada
            jornadaService.crearJornada(jornada);
            */
        }

        /*
        // Obtener las jornadas de la liga
        List<JornadaId> jornadas = new ArrayList<>();
        for(Jornada jornada : jornadaList){
            jornadas.add(jornada.getId());
        }

        // Actualizar liga
        liga.setJornadas(jornadas);
        */

        // Actualizar estado liga
        liga.setEstado(ESTADO.EN_CURSO);
        ligaService.crearLiga(liga);

        return ResponseEntity.status(HttpStatus.OK).body("Liga iniciada");
    }

    @GetMapping("/{leagueId}/match")
    public ResponseEntity<List<EnfrentamientoDTO>> obtenerEnfrentamientos(@PathVariable String leagueId) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
}
