package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ErrorResponseDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTORegister;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.security.AuthenticationService;
import com.example.gironetaServer.infraestructure.adapters.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v0/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    // Not used Mappers for conversion between Domain and Entity (no idea where it
    // should be)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTORegister registerUserDto) {
        try {
            authenticationService.signup(registerUserDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponseDto("Conflict", "El correo electrónico ya está registrado: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error", "Ocurrió un error inesperado: " + e.getMessage()));
        }
    }

    // Not used Mappers for conversion between Domain and Entity (no idea where it
    // should be)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getId(), authenticatedUser.getEmail());
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
    // Clase interna para la respuesta del login
    public static class LoginResponse {
        private String token;
        private String type = "Bearer";
        private long expiresIn;

        public LoginResponse() {
        }

        public String getToken() {
            return token;
        }

        public LoginResponse setToken(String token) {
            this.token = token;
            return this;
        }

        public String getType() {
            return type;
        }

        public LoginResponse setType(String type) {
            this.type = type;
            return this;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public LoginResponse setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }
    }
}
