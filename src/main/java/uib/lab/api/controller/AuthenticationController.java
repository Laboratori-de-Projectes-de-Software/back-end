package uib.lab.api.controller;

import uib.lab.api.dto.user.UserRegistrationRequest;
import uib.lab.api.service.AuthenticationService;
import uib.lab.api.util.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiMessage> register(@Valid @RequestBody UserRegistrationRequest user, Locale locale) throws MessagingException {
        var message = authenticationService.register(user, locale);
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
