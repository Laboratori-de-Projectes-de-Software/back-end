package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.domain.User;

import java.util.List;

public interface UserRepository {
    User getUserById(Long id);

    User createUser(User user);

    List<User> getUsers();

    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
