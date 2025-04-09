package jaumesitos.backend.demo.infrastructure.res.api;

import jaumesitos.backend.demo.application.service.AuthService;
import jaumesitos.backend.demo.application.service.BotService;
import jaumesitos.backend.demo.domain.Bot;
import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.res.dto.BotDTO;
import jaumesitos.backend.demo.infrastructure.res.dto.BotResponseDTO;
import jaumesitos.backend.demo.infrastructure.res.mapper.BotDTOMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jaumesitos.backend.demo.infrastructure.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
@Tag(name = "Bot Controller", description = "Endpoints for managing bots")
public class BotController {

    private final BotService botService;
    private final BotDTOMapper botMapper;
    private final AuthService authService;

    @Operation(summary = "Create a new bot", description = "Registers a new bot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bot created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BotResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PostMapping("/bot")
    public ResponseEntity<?> createBot(@RequestBody BotDTO dto) {
        try {
            String email = AuthUtil.getCurrentUserEmail();

            User user = authService.getUserByEmail(email);

            Bot bot = botMapper.toDomain(dto);
            bot.setOwnerId(user.getId());

            Bot saved = botService.registerBot(bot);
            BotResponseDTO response = botMapper.toResponseDTO(saved);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating bot");
        }
    }




    @Operation(summary = "Get all bots or by user", description = "Returns all bots or those from a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bots found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BotResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping("/bot")
    public ResponseEntity<?> getAllBots(@Parameter(description = "ID of the bot owner", required = false) @RequestParam(required = false) Integer owner) {
        try {
            List<Bot> bots = (owner == null)
                    ? botService.getAllBots()
                    : botService.getBotsByOwner(owner);
            List<BotResponseDTO> response = botMapper.toResponseDTOList(bots);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting all bots");
        }
    }

    @Operation(summary = "Get one bot", description = "Returns a bot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bot found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BotResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Bot not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping("/bot/{botId}")
    public ResponseEntity<BotResponseDTO> getBotById(@PathVariable int botId) {
        Bot bot = botService.getBotById(botId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bot not found"));

        return ResponseEntity.ok(botMapper.toResponseDTO(bot));
    }



    @Operation(summary = "Update one bot", description = "Updates a bot by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bot updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BotResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Bot not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PutMapping("/bot/{botId}")
    public ResponseEntity<?> updateBot(@PathVariable int botId, @RequestBody BotDTO dto) {
        try {
            Bot botToUpdate = botMapper.toDomain(dto);
            Bot updated = botService.updateBot(botId, botToUpdate);
            BotResponseDTO response = botMapper.toResponseDTO(updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating bot");
        }
    }

}
