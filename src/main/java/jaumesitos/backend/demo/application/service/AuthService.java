package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.application.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;

    public void register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        User user = userOpt.get();

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }
}
