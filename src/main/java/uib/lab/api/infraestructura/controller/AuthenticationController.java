package uib.lab.api.infraestructura.controller;

import uib.lab.api.application.dto.user.UserDTORegister;
import uib.lab.api.application.service.AuthenticationService;
import uib.lab.api.infraestructura.util.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiMessage> register(@Valid @RequestBody UserDTORegister user, Locale locale) {
        var message = authenticationService.register(user, locale);
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
