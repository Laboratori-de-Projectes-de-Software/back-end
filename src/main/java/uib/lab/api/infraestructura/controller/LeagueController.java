package uib.lab.api.infraestructura.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import uib.lab.api.application.dto.League.LeagueRequest;
import uib.lab.api.domain.entity.League;
import uib.lab.api.infraestructura.util.ApiMessage;
import uib.lab.api.application.service.LeagueService;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/league")
@PreAuthorize("isAuthenticated()")
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<ApiMessage> createLeague(@Valid @RequestBody LeagueRequest leagueRequest, Locale locale) {
        var message = leagueService.createLeague(leagueRequest, locale);
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
