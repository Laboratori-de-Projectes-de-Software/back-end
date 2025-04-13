package com.example.demo.adapters.in.web;

import com.example.demo.application.port.in.UserUseCase;
import com.example.demo.application.service.JwtService;
import com.example.demo.dtos.UserDTOLogin;
import com.example.demo.dtos.UserDTORegister;
import com.example.demo.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final UserUseCase userUseCase;

    public AuthenticationController(JwtService jwtService, UserUseCase userUseCase) {
        this.jwtService = jwtService;
        this.userUseCase = userUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTORegister> register(@RequestBody UserDTORegister userDTORegister) {
        UserDTORegister registeredUser = userUseCase.register(userDTORegister);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDTOLogin userDTOLogin) {

        UserDTOLogin authenticatedUser = userUseCase.login(userDTOLogin);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Obtén la hora actual con la zona horaria del sistema
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime expirationTime = currentTime.plusNanos(jwtService.getExpirationTime());
        Date expiresInDate = Date.from(expirationTime.toInstant());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setToken(jwtToken);
        userResponseDTO.setExpiresIn(new Date((new Date()).getTime() + jwtService.getExpirationTime()*3));  // Fecha actual
        userResponseDTO.setUser(userDTOLogin.getUsername());

        return ResponseEntity.ok(userResponseDTO);
    }
}
