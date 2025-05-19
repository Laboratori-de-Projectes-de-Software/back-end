package com.debateia.adapter.in.rest.auth;

import com.debateia.application.ports.in.rest.AuthUseCase;

import com.debateia.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTORegister request) {
        try {
            User user = authUseCase.register(request);
            UserDTO response = new UserDTO(user.getUserId(), user.getUsername(), user.getMail());
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTOLogin loginRequest) {
        try {
            User user = authUseCase.authenticate(loginRequest);
            TokenData td = authUseCase.generateTokens(user);
            UserResponseDTO response = new UserResponseDTO(td.accessToken(), td.expiresIn(), user.getUsername(), user.getUserId(), user.getMail());
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @PostMapping("/update-credentials")
    public ResponseEntity<?> updateCredentials(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @RequestBody UpdateCredRequest request) {
        User updatedUser = authUseCase.updateCred(authentication, request);
        try {
            TokenData td = authUseCase.generateTokens(updatedUser);
            final UserResponseDTO response = new UserResponseDTO(td.accessToken(), td.expiresIn(), updatedUser.getUsername(), updatedUser.getUserId(), updatedUser.getMail());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<UserResponseDTO> refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        try {
            User user = authUseCase.refreshToken(authentication);
            TokenData td = authUseCase.generateTokens(user);
            return ResponseEntity.ok(new UserResponseDTO(td.accessToken(), td.expiresIn(), user.getUsername(), user.getUserId(), user.getMail()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Puedes retornar un mensaje de error si lo prefieres
        }
    }


}
