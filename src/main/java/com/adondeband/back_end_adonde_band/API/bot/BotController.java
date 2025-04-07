package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.bot.BotImpl;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenImpl;
import com.adondeband.back_end_adonde_band.dominio.imagen.ImagenService;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import jakarta.transaction.Transactional;
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


    @Autowired
    public BotController(BotImpl botService, ImagenImpl imagenService, BotDtoMapper botDtoMapper) {
        this.botService = botService;
        this.imagenService = imagenService;
        this.botMapper = botDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<BotDTOMin>> listarBots(@RequestParam(value = "owner", required = false) String userId) {
        // Obtener listado de bots
        List<Bot> bots = (userId == null)
                ? botService.obtenerTodosLosBots()
                : botService.obtenerBotsPorUsuario(new UsuarioId(
                    SecurityContextHolder.getContext().getAuthentication().getName()));

        // comprobar que la lista no está vacía
        if (bots.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // pasar de Bot a BotDTOMin
        List<BotDTOMin> botsDTOMin = bots.stream().map(botMapper::toDTO).map(BotDTOMin::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(botsDTOMin);
    }

    @PostMapping
    public ResponseEntity<BotDTO> crearBot(@RequestBody BotDTOMin botDTOMin) {
        System.out.println("LLAMANDO A CREARBOT");

        BotDTO botDTO = new BotDTO(botDTOMin);
        Bot bot = botMapper.toDomain(botDTO);

        // Obtiene el usuario autenticado desde SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Set UserId into bot
        bot.setUsuario(new UsuarioId(authentication.getName()));

        // Guardar imagen del bot (por si no existe)
        Imagen imagenSaved = imagenService.guardarImagen(bot.getImagen());
        bot.setImagen(imagenSaved);

        // Guardar bot y obtener el nuevo bot proveniente de la BD
        Bot nuevoBot = botService.crearBot(bot);

        System.out.println("Bot creado: " + nuevoBot.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(botMapper.toDTO(nuevoBot));
    }

    @GetMapping("/{botId}")
    public ResponseEntity<List<BotDTO>> obtenerBot(@PathVariable String botId) {
        // Obtener la lista de Bots desde el servicio
        List<Bot> bots = botService.obtenerBotPorNombre(botId);

        // Convertir manualmente de Bot a BotDTO
        List<BotDTO> botDTO = new ArrayList<>();
        botDTO.add(botMapper.toDTO(bots.getFirst()));

        // Devolver la lista de BotDTO en la respuesta HTTP
        return ResponseEntity.status(HttpStatus.OK).body(botDTO);
    }
}
