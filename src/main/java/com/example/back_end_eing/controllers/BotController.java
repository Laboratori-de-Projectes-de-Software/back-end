package com.example.back_end_eing.controllers;


import com.example.back_end_eing.dto.BotDTO;
import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;
import com.example.back_end_eing.exceptions.BotNotFoundException;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.services.BotService;
import com.example.back_end_eing.services.CloudinaryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v0/bot")
public class BotController {

    @Autowired
    private BotService botService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private BotRepository botRepository;

    @PutMapping
    public ResponseEntity<BotResponseDTO> registrarBot(@RequestBody BotDTO botdto) {
        String urlFoto;
        if (botdto.getUrlImagen() == null || botdto.getUrlImagen().isEmpty()) {
            urlFoto = null;
        }else {
            try {

                urlFoto = cloudinaryService.uploadBase64(botdto.getUrlImagen());
            } catch (IOException e) {
                return ResponseEntity.status(500).body(new BotResponseDTO());
            }
        }
        botdto.setUrlImagen(urlFoto);
        BotResponseDTO responseDTO = botService.BotRegistro(botdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<BotSummaryResponseDTO>> listarBots(@RequestParam Long owner) {

        return ResponseEntity.ok(botService.listarBots(owner));

    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> obtenerBot(@PathVariable("botId") Long botId) {

        return ResponseEntity.ok(botService.obtenerBot(botId));

    }

    @PutMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> uptadeBot(@RequestBody BotDTO botdto,
                                            @PathVariable("botId") Long botId) throws IOException {

                                                String urlFoto;
        if (botdto.getUrlImagen() == null || botdto.getUrlImagen().isEmpty()) {
            urlFoto = null;
        }else {
            urlFoto = cloudinaryService.uploadBase64(botdto.getUrlImagen());
        }
        botdto.setUrlImagen(urlFoto);
        BotResponseDTO responseDTO = botService.actualizarBot(botdto, botId);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BotSummaryResponseDTO>> getAllBots() {

        return ResponseEntity.ok(botService.getAllBots());

    }

    @GetMapping("/{botId}/byid")
    public ResponseEntity<BotDTO> getLigaById(@PathVariable Long botId) {

        Bot bot = botService.getBotById(botId);
        int id = bot.getUsuario().getId().intValue();
        BotDTO botDTO = new BotDTO(bot.getNombreBot(), bot.getDescripcionBot(), bot.getFotoBot(), bot.getApiKey(), id);
        return new ResponseEntity<>(botDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{botId}")
    public ResponseEntity<Void> deleteBot(@PathVariable Long botId) {

        botService.deleteBot(botId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
