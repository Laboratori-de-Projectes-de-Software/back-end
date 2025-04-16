package com.example.gironetaServer.infraestructure.adapters.security;

import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTOLogin;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTORegister;
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

    public UserEntity signup(UserDTORegister input) {
        // Verificar si el username ya está registrado
        if (userJpaRepository.findByUsername(input.getUser()).isPresent()) {
            throw new ConflictException("The username '" + input.getUser() + "' is already taken");
        }

        UserEntity user = new UserEntity();
        user.setUsername(input.getUser());
        user.setEmail(input.getMail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        try {
            return userJpaRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Check if the exception is due to a unique constraint violation on the email
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                throw new ConflictException("The email '" + input.getMail() + "' is already registered");
            }
            // Rethrow the exception if it's a different integrity violation
            throw e;
        }
    }

    public UserEntity authenticate(UserDTOLogin input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUser(),
                            input.getPassword()));

            return userJpaRepository.findByUsername(input.getUser())
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
