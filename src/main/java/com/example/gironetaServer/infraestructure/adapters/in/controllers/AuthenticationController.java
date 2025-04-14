package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ErrorResponseDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTOLogin;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.UserMapper;
import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTORegister;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.security.AuthenticationService;
import com.example.gironetaServer.infraestructure.adapters.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v0/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserMapper userMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
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
    public ResponseEntity<?> authenticate(@RequestBody UserDTOLogin loginUserDto) {
        try {
            UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getId(), authenticatedUser.getEmail());
            UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(jwtToken, jwtService.getExpirationTime(), authenticatedUser.getUsername(), authenticatedUser.getId());
            return ResponseEntity.ok(userResponseDTO);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Unauthorized", "Invalid credentials: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponseDto("Forbidden", "Access denied: " + e.getMessage()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Not Found", "Resource not found: " + e.getMessage()));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Internal Server Error", "Feature not implemented: " + e.getMessage()));
        }
    }
}
