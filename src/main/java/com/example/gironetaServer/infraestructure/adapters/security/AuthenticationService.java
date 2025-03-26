package com.example.gironetaServer.infraestructure.adapters.security;

import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.RegisterUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.UserMapper;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.repository.UserJpaRepository;
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
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userJpaRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        UserEntity user = new UserEntity();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        UserEntity savedUser = userJpaRepository.save(user);
        return UserMapper.toDomain(savedUser);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        UserEntity userEntity = userJpaRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserMapper.toDomain(userEntity);
    }
}

