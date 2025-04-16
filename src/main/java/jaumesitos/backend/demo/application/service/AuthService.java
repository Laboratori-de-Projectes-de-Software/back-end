package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.application.repository.IUserRepository;
import jaumesitos.backend.demo.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public void register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String login(String name, String password) {
        Optional<User> userOpt = userRepository.findByName(name);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return tokenProvider.generateToken(user.getEmail(), user.getRole());
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByName(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
