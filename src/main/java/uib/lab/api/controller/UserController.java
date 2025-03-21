package uib.lab.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.entity.Bot;
import uib.lab.api.service.BotService;
import uib.lab.api.service.UserService;
import uib.lab.api.dto.user.UserResponse;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;
    private final BotService botService;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable long id, Locale locale) {
        return userService.findById(id, locale);
    }

    @GetMapping("/{id}/bots")
    public ResponseEntity<List<Bot>> getBotsByUser(@PathVariable Long id) {
        List<Bot> bots = botService.getBotsByUser(id);
        return ResponseEntity.ok(bots);
    }
}
