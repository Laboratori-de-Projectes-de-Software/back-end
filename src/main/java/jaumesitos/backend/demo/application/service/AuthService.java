package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.application.repository.IUserRepository;
import jaumesitos.backend.demo.infrastructure.res.dto.UserDTORegister;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        return user;
    }
}
