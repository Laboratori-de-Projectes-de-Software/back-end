package com.debateia.adapter.in.web;

import com.debateia.application.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.debateia.adapter.in.web.UserDTOLogin;
import com.debateia.adapter.in.web.UserDTORegister;
import com.debateia.adapter.out.persistence.UserResponseDTO;
import com.debateia.adapter.in.web.UpdateCredRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTORegister request) {
        final UserResponseDTO response = service.register(request);
        if (response == null) {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserDTOLogin request) {
        final UserResponseDTO response = service.authenticate(request);
        if (response == null) {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-credentials")
    public ResponseEntity<?> updateCredentials(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication,
            @RequestBody UpdateCredRequest request) {
        final UserResponseDTO response = service.updateCred(authentication, request);
        if (response == null) {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public UserResponseDTO refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        return service.refreshToken(authentication);
    }

}
