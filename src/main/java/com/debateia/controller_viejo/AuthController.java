package com.debateia.controller_viejo;


import com.debateia.JWT_viejo.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.debateia.dto_viejo.AuthRequest;
import com.debateia.dto_viejo.RegisterRequest;
import com.debateia.dto_viejo.TokenResponse;
import com.debateia.dto_viejo.UpdateCredRequest;

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
