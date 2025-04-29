package uib.lab.api.infraestructure.controller;

import uib.lab.api.application.dto.user.UserDTORegister;
import uib.lab.api.application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.infraestructure.util.ApiResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth/register")
    public ApiResponse register(@Valid @RequestBody UserDTORegister user) {
        return authenticationService.register(user);
    }
}
