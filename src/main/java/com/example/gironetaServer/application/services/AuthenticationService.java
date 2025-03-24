package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateAndGenerateToken(String usernameOrEmail, String password) {
        User user = userRepository.getUserByUsername(usernameOrEmail);
        if (user == null) {
            user = userRepository.getUserByEmail(usernameOrEmail);
        }

        if (user == null) {
            throw new IllegalArgumentException("Invalid username or email");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
        return jwtTokenUtil.generateToken(userDetails);
    }

    public void registerUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, email, encodedPassword, LocalDateTime.now(), LocalDateTime.now());
        userRepository.createUser(user);
    }
}