package uib.lab.api.infraestructure.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uib.lab.api.application.dto.bot.BotDTO;
import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.service.BotService;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/bot")
@PreAuthorize("isAuthenticated()")
public class BotController {
    private final BotService botService;

    @PostMapping
    public BotResponseDTO createBot(@Valid @RequestBody BotDTO botDTO, Locale locale) {
        return botService.save(botDTO, locale);
    }
}
