package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.application.usecases.users.CreateUser;
import com.example.gironetaServer.application.usecases.users.GetUser;
import com.example.gironetaServer.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements GetUser, CreateUser {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
