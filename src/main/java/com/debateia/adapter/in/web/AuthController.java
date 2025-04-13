package com.debateia.adapter.in.web;

import com.debateia.application.jwt.TokenData;
import com.debateia.application.mapper.UserMapper;
import com.debateia.application.service.AuthService;

import com.debateia.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.debateia.adapter.in.web.dto.request.UpdateCredRequest;
import com.debateia.adapter.in.web.dto.request.UserDTOLogin;
import com.debateia.adapter.in.web.dto.request.UserDTORegister;
import com.debateia.adapter.out.persistence.UserResponseDTO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTORegister request) {
        try {
            User user = authService.register(request);
            TokenData td = authService.generateTokens(user);
            UserResponseDTO response = new UserResponseDTO(
                    td.accessToken(), td.expiresIn(), user.getUsername());
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTOLogin loginRequest) {
        try {
            User user = authService.authenticate(loginRequest);
            TokenData td = authService.generateTokens(user);
            UserResponseDTO response = new UserResponseDTO(td.accessToken(), td.expiresIn(), user.getUsername());
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }


    @PostMapping("/update-credentials")
    public ResponseEntity<?> updateCredentials(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @RequestBody UpdateCredRequest request) {
        User updatedUser = authService.updateCred(authentication, request);
        try {
            TokenData td = authService.generateTokens(updatedUser);
            final UserResponseDTO response = new UserResponseDTO(td.accessToken(), td.expiresIn(), updatedUser.getUsername());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<UserResponseDTO> refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        try {
            User user = authService.refreshToken(authentication);
            TokenData td = authService.generateTokens(user);
            return ResponseEntity.ok(new UserResponseDTO(td.accessToken(), td.expiresIn(), user.getUsername()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Puedes retornar un mensaje de error si lo prefieres
        }
    }


}
