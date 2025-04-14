package com.example.gironetaServer.infraestructure.adapters.security;

import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTOLogin;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.RegisterUserDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.UserJpaRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserJpaRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userJpaRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(RegisterUserDto input) {
        UserEntity user = new UserEntity();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        try {
            UserEntity savedUser = userJpaRepository.save(user);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            // Verificar si es una violación de clave única para el email
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                throw new ConflictException("El correo electrónico '" + input.getEmail() + "' ya está registrado");
            }
            // Si es otra violación de integridad, relanzar la excepción
            throw e;
        }
    }

    public UserEntity authenticate(UserDTOLogin input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()));

            return userJpaRepository.findByEmail(input.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Invalid credentials: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ForbiddenException("Access denied: " + e.getMessage());
        } catch (UnsupportedOperationException e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
    }
}
