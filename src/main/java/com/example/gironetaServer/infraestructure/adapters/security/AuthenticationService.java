package com.example.gironetaServer.infraestructure.adapters.security;

import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDTORegister;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.UserJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
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
        UserEntity user = new UserEntity();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        try {
            return userJpaRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Check if the exception is due to a unique constraint violation on the email
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                throw new ConflictException("The email '" + input.getEmail() + "' is already registered");
            }
            // Rethrow the exception if it's a different integrity violation
            throw e;
        }
    }

    public UserEntity authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        UserEntity userEntity = userJpaRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // return UserMapper.toDomain(userEntity); Lo pasa a dominio (?
        return userEntity;
    }
}
