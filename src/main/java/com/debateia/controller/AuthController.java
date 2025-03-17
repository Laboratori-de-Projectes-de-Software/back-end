package com.debateia.controller;


import com.debateia.JWT.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.debateia.dto.AuthRequest;
import com.debateia.dto.RegisterRequest;
import com.debateia.dto.TokenResponse;
import com.debateia.dto.UpdateCredRequest;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        final TokenResponse response = service.register(request);
        if(response==null)
        {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        final TokenResponse response = service.authenticate(request);
        if(response==null)
        {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/update-credentials")
    public ResponseEntity<?> updateCredentials(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication, @RequestBody UpdateCredRequest request) {

        final TokenResponse response = service.updateCred(authentication,request);
        if(response==null)
        {
            return ResponseEntity.badRequest().body("Registration failed: Invalid request.");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return service.refreshToken(authentication);
    }


}
