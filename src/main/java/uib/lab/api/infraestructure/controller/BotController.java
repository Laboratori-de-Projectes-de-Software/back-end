package uib.lab.api.infraestructure.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.application.dto.bot.BotDTO;
import uib.lab.api.application.service.BotService;
import uib.lab.api.infraestructure.util.ApiResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/bot")
@PreAuthorize("isAuthenticated()")
public class BotController {
    private final BotService botService;

    @PostMapping
    public ApiResponse createBot(@Valid @RequestBody BotDTO botDTO) {
        return botService.save(botDTO);
    }

    @GetMapping
    public ApiResponse getBotsByUser(@RequestParam(required = false) Integer owner) {
        return botService.getBotsByUser(owner);
    }

    @GetMapping("/{botId}")
    public ApiResponse getBotById(@PathVariable int botId) {
        return botService.getBotById(botId);
    }

    @PutMapping("/{botId}")
    public ApiResponse updateBot(@PathVariable int botId, @Valid @RequestBody BotDTO request) {
        return botService.updateBot(botId, request);
    }
}
