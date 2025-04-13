package com.example.demo.application.service;

import com.example.demo.adapters.out.persistence.user.UserEntity;
import com.example.demo.application.port.in.UserUseCase;
import com.example.demo.application.port.out.UserRepository;
import com.example.demo.domain.model.User;
import com.example.demo.dtos.UserDTOLogin;
import com.example.demo.dtos.UserDTORegister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserUseCase {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTORegister register(UserDTORegister input) {
        User user = new User(input.getUsername(), input.getMail(), passwordEncoder.encode(input.getPassword()));

        User savedUser = userRepository.register(user);

        return toDTOregister(savedUser);
    }

    public UserDTOLogin login(UserDTOLogin input) {

        User user = userRepository.findByUsername(input.getUsername());

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), input.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        return toDTOlogin(user);

    }

    private UserDTORegister toDTOregister(User user) {
        UserDTORegister dto = new UserDTORegister();
        dto.setUsername(user.getUsername());
        dto.setMail(user.getMail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    private UserDTOLogin toDTOlogin(User user) {
        UserDTOLogin dto = new UserDTOLogin();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
