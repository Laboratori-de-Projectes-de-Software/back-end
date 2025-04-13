package com.example.demo.adapters.in.web;

import com.example.demo.application.port.in.BotUseCase;
import com.example.demo.domain.model.User;
import com.example.demo.dtos.BotDTO;
import com.example.demo.dtos.BotResponseDTO;
import com.example.demo.dtos.BotSummaryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para gestionar ligas.
 */
@RestController
@RequestMapping("/bot")
public class BotController {

    private final BotUseCase botUseCase;

    public BotController(BotUseCase botUseCase) {
        this.botUseCase = botUseCase;
    }

    @PostMapping
    public ResponseEntity<BotResponseDTO> createBot(@RequestBody BotDTO leagueDTO) {
        BotResponseDTO created = botUseCase.createBot(leagueDTO);

        return ResponseEntity.ok(created);
    }


    @GetMapping
    public ResponseEntity<List<BotSummaryResponseDTO>> listBots(@RequestParam(required = false) Integer owner) {
        Integer ownerId = owner;

        // Si no se pasó owner, lo intentamos obtener del usuario autenticado
        if (ownerId == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof User user) {
                ownerId = user.getId(); // <- Aquí asegúrate que el objeto 'user' tiene getId()
            }
        }

        List<BotSummaryResponseDTO> bots = (ownerId != null)
                ? botUseCase.listBotsByOwner(ownerId)
                : botUseCase.listBots();

        return ResponseEntity.ok(bots);
    }

    @GetMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> getBot(@PathVariable Integer botId) {
        BotResponseDTO bot = botUseCase.getBot(botId);
        return ResponseEntity.ok(bot);
    }

    @PutMapping("/{botId}")
    public ResponseEntity<BotResponseDTO> updateBot(@PathVariable Integer botId, @RequestBody BotDTO botDTO) {
        BotResponseDTO updated = botUseCase.updateBot(botId, botDTO);
        return ResponseEntity.ok(updated);
    }

}
