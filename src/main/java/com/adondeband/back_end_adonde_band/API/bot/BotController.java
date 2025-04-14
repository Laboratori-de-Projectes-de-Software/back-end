package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.bot.BotImpl;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenImpl;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenService;
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
import java.util.List;

@RestController
@RequestMapping("api/v0/bot")
public class BotController {

    private final BotService botService;
    private final ImagenService imagenService;
    private final BotDtoMapper botMapper;
    private final UsuarioService usuarioService;


    @Autowired
    public BotController(BotImpl botService, ImagenImpl imagenService, BotDtoMapper botDtoMapper, UsuarioService usuarioService) {
        this.botService = botService;
        this.imagenService = imagenService;
        this.botMapper = botDtoMapper;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<BotDTOMin>> listarBots(@RequestParam(value = "owner", required = false) Long userId) {
        // Obtiene el nombre del usuario autenticado desde SecurityContextHolder
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Set UserId en el bot
        // Obtener listado de bots
        List<Bot> bots;
        try {
            bots = (userId == null)
                    ? botService.obtenerTodosLosBots()
                    : botService.obtenerBotsPorIdUsuario(new UsuarioId(userId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // pasar de Bot a BotDTOMin
        List<BotDTOMin> botsDTOMin = bots.stream().map(botMapper::toDTO).map(BotDTOMin::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(botsDTOMin);
    }

    @PostMapping
    public ResponseEntity<BotDTOResponse> crearBot(@RequestBody BotDTOMin botDTOMin) {
        BotDTOResponse botDTO = new BotDTOResponse(botDTOMin);
        Bot bot = botMapper.toDomain(botDTO);

        // Obtiene el nombre del usuario autenticado desde SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Obtener el id de usuario autenticado
        Usuario user = usuarioService.obtenerUsuarioPorNombre(authentication.getName());
        // Set UserId en el bot
        bot.setUsuario(user.getId());

        // Guardar imagen del bot (por si no existe)
        Imagen imagenSaved = imagenService.guardarImagen(bot.getImagen());
        bot.setImagen(imagenSaved);

        Bot nuevoBot = null;
        // Guardar bot y obtener el nuevo bot proveniente de la BD
        try {
            nuevoBot = botService.crearBot(bot);
        } catch (IllegalArgumentException e) {
            // El bot ya existe
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(botMapper.toDTO(nuevoBot));
    }

    @GetMapping("/{botId}")
    public ResponseEntity<List<BotDTOResponse>> obtenerBot(@PathVariable String botId) {
        // Obtener la lista de Bots desde el servicio
        List<Bot> bots = botService.obtenerBotPorNombre(botId);

        if (bots.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Convertir manualmente de Bot a BotDTO
        List<BotDTOResponse> botDTO = new ArrayList<>();
        botDTO.add(botMapper.toDTO(bots.getFirst()));

        // Devolver la lista de BotDTO en la respuesta HTTP
        return ResponseEntity.status(HttpStatus.OK).body(botDTO);
    }
}
