package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IUserRepository;
import jaumesitos.backend.demo.domain.User;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) { this.userRepository = userRepository; }

    public void registerUser(String name, String email, String password, String role) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) { throw new IllegalArgumentException("El usuario ya existe"); }

        User user = new User(UUID.randomUUID().toString(), name , email, password, role);
        userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
