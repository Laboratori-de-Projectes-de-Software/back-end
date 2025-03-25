package com.example.gironetaServer.infraestructure.adapters.security;

import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.RegisterUserDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.UserJpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userJpaRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        UserEntity user = new UserEntity()
                .setUsername(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userJpaRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        UserEntity userEntity = userJpaRepository.findByEmail(input.getEmail())
                .orElseThrow();
        return UserMapper.toDomain(userEntity);
    }
}

