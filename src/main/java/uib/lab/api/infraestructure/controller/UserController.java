package uib.lab.api.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.application.dto.user.UserResponse;
import uib.lab.api.application.dto.user.UserUpdateRequest;
import uib.lab.api.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.infraestructure.entity.Bot;
import uib.lab.api.application.service.BotService;

import uib.lab.api.infraestructure.util.ApiMessage;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/user")
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

    @GetMapping("/users")
    public ResponseEntity<List<UserDomain>> getAllUsers() {
        List<UserDomain> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiMessage> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest user, Locale locale) throws MessagingException {
        var message = userService.updateUser(id, user, locale);
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
